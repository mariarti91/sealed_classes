package ru.mariarti.sealedtest.domain

import ru.mariarti.sealedtest.repository.AdvertsRepository
import ru.mariarti.sealedtest.repository.IAdvertsRepository
import ru.mariarti.sealedtest.repository.IOffersRepository
import ru.mariarti.sealedtest.repository.OffersRepository

class FeedInteractor(
    private val offersRepo: IOffersRepository = OffersRepository(),
    private val advertsRepo: IAdvertsRepository = AdvertsRepository()
) {
    fun getFeedItems(): List<FeedItem> = offersRepo.getItems(100)
        .mapIndexed { idx, item ->
            when {
                idx % 4  == 3 -> listOf(item) + advertsRepo.getItems(1)
                else -> listOf(item)
            }
        }
        .flatten()
}