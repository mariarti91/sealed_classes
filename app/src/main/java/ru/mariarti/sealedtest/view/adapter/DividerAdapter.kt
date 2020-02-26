package ru.mariarti.sealedtest.view.adapter

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import ru.mariarti.sealedtest.R

object DividerAdapter :KDelegateAdapter<DividerViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_divider

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] === DividerViewModel

    override fun onBind(item: DividerViewModel, viewHolder: KViewHolder) {
        //Do nothing
    }
}

object DividerViewModel: IComparableItem{
    override fun id(): Any = this
    override fun content(): Any = this
}