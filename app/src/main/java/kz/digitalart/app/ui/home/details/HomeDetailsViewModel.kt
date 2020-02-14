package kz.digitalart.app.ui.home.details

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.RatingModel
import javax.inject.Inject

class HomeDetailsViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository,
    val prefsImpl: PrefsImpl
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName

    val ratingModelData: MutableLiveData<RatingModel> by lazy { MutableLiveData<RatingModel>() }

    fun setExhibitRate(id: Int?, rating: Double) {
        doWork {
            val ratingModel = baseCloudRepository.setExhibitRate(id, rating)
            ratingModelData.postValue(ratingModel)
        }
    }
}