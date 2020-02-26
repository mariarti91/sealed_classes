package ru.mariarti.sealedtest.view.adapter

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_offer.*
import ru.mariarti.sealedtest.R
import ru.mariarti.sealedtest.domain.FeedItem

class OfferAdapter(
    private val onClick: (payload: FeedItem.Offer) -> Unit
):KDelegateAdapter<OfferViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_offer

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is OfferViewModel

    override fun onBind(item: OfferViewModel, viewHolder: KViewHolder) = with(viewHolder){
        tvTitle.text = item.title
        tvText.text = item.text
        tvPrice.text = item.price
        itemView.setOnClickListener { onClick(item.payload) }
    }
}

class OfferViewModel(
    val title: String,
    val price: String,
    val text: String,
    val payload: FeedItem.Offer
) : IComparableItem{
    override fun id(): Any = this::class.java
    override fun content(): Any = this

}