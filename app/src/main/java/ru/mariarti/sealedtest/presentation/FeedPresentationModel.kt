package ru.mariarti.sealedtest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.delegateadapter.delegate.diff.IComparableItem
import ru.mariarti.sealedtest.domain.FeedInteractor
import ru.mariarti.sealedtest.domain.FeedItem

class FeedPresentationModel(
    private val interactor: FeedInteractor = FeedInteractor(),
    private val viewModelFactory: FeedVMFactory = FeedVMFactory()
):ViewModel(){
    val modelLiveData by lazy {
        MutableLiveData<FeedViewModel>()
    }

    private var model: Model

    init{
        model = Model(interactor.getFeedItems())
        update()
    }

    fun onOfferClicked(offer: FeedItem.Offer){
        update{
            copy(
                items = listOfNotNull(
                    items.firstOrNull{ item ->
                        item is FeedItem.Offer && item.id == offer.id
                    }
                )
                 + items.filterNot {item ->
                    item is FeedItem.Offer && item.id == offer.id
                }
            )
        }
    }

    fun onHideAdvertClicked(advert: FeedItem.Advert){
        update{
            copy(
                items = items.filterNot{item ->
                    item is FeedItem.Advert && item.id == advert.id
                }
            )
        }
    }

    private fun update(mapper: Model.() -> Model = { this }){
        model = model.mapper()
        val viewModel = viewModelFactory.toViewModel(model)
        modelLiveData.value = viewModel
    }

    data class Model(
        val items: List<FeedItem>
    )
}

data class FeedViewModel(
    var items: List<IComparableItem>
)