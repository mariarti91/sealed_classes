package ru.mariarti.sealedtest.view.adapter

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_advert.*
import ru.mariarti.sealedtest.R
import ru.mariarti.sealedtest.domain.FeedItem

class AdvertAdapter(
    private val onClick: (payload: FeedItem.Advert) -> Unit
):KDelegateAdapter<AdvertViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_advert

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is AdvertViewModel

    override fun onBind(item: AdvertViewModel, viewHolder: KViewHolder) = with(viewHolder){
        bHide.setOnClickListener { onClick(item.payload) }
    }
}

class AdvertViewModel(
    val payload: FeedItem.Advert
) : IComparableItem{
    override fun id(): Any = this::class.java
    override fun content(): Any = this

}