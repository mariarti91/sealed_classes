package ru.mariarti.sealedtest.domain

sealed class FeedItem(open val id: String) {
    data class Offer(
        override val id: String,
        val title:String,
        val text: String,
        val price: Int
    ): FeedItem(id)

    data class Advert(
        override val id: String
    ):FeedItem(id)
}