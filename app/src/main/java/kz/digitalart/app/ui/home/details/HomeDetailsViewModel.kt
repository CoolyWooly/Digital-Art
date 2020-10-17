package kz.digitalart.app.ui.home.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.cloud.ResultWrapper
import kz.digitalart.app.data.cloud.repository.BaseCloudRepository
import kz.digitalart.app.data.db.PrefsImpl
import kz.digitalart.app.domain.model.RatingModel

class HomeDetailsViewModel @ViewModelInject constructor(
    private val baseCloudRepository: BaseCloudRepository,
    val prefsImpl: PrefsImpl
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName

    val ratingModelData: MutableLiveData<RatingModel> by lazy { MutableLiveData<RatingModel>() }
    val error: MutableLiveData<ResultWrapper.Error> by lazy { MutableLiveData<ResultWrapper.Error>() }

    fun setExhibitRate(id: Int?, rating: Double) {
        launchIO {
            when (val ratingModel = baseCloudRepository.setExhibitRate(id, rating)) {
                is ResultWrapper.Error -> error.postValue(ratingModel)
                is ResultWrapper.Success -> ratingModelData.postValue(ratingModel.value)
            }
        }
    }
}