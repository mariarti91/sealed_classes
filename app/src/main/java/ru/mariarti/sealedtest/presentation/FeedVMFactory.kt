package ru.mariarti.sealedtest.presentation

import ru.mariarti.sealedtest.domain.FeedItem
import ru.mariarti.sealedtest.view.adapter.AdvertViewModel
import ru.mariarti.sealedtest.view.adapter.DividerViewModel
import ru.mariarti.sealedtest.view.adapter.OfferViewModel
import java.text.DecimalFormat

class FeedVMFactory {

    private val decimalFormat = DecimalFormat("###,###.#")

    fun toViewModel(model: FeedPresentationModel.Model): FeedViewModel{
        val items = model.items.map{item ->
            when(item){
                is FeedItem.Offer -> item.toViewModel()
                is FeedItem.Advert -> item.toViewModel()
            }
        }
        val itemsWithDivider = items
            .map{ item -> listOf(item, DividerViewModel) }
            .flatten()
        return FeedViewModel(itemsWithDivider)
    }

    private fun FeedItem.Offer.toViewModel(): OfferViewModel = OfferViewModel(
        title = title,
        price = decimalFormat.format(price) + " P",
        text = text,
        payload = this
    )

    private fun FeedItem.Advert.toViewModel(): AdvertViewModel = AdvertViewModel(
        payload = this
    )
}