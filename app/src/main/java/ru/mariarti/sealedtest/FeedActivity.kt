package ru.mariarti.sealedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.activity_feed.*
import ru.mariarti.sealedtest.presentation.FeedPresentationModel
import ru.mariarti.sealedtest.presentation.FeedViewModel
import ru.mariarti.sealedtest.view.adapter.AdvertAdapter
import ru.mariarti.sealedtest.view.adapter.DividerAdapter
import ru.mariarti.sealedtest.view.adapter.OfferAdapter

class FeedActivity : AppCompatActivity() {

    private lateinit var presentation: FeedPresentationModel

    private val adapter:DiffUtilCompositeAdapter by lazy { getDiffAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initPresentationModel()
        bindRecycler()
    }

    private fun initPresentationModel() {
        presentation = ViewModelProvider(this)[FeedPresentationModel::class.java]
        presentation.modelLiveData.observe(this, Observer { model: FeedViewModel ->
            update(model)
        })
    }

    private fun bindRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun update(model: FeedViewModel){
        adapter.swapData(model.items)
    }

    private fun getDiffAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(OfferAdapter(presentation::onOfferClicked))
        .add(AdvertAdapter(presentation::onHideAdvertClicked))
        .add(DividerAdapter)
        .build()
}
