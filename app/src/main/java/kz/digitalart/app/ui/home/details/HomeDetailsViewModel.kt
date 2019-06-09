package kz.digitalart.app.ui.home.details

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.Rating
import javax.inject.Inject

class HomeDetailsViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository,
    val prefsImpl: PrefsImpl
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName

    val ratingData: MutableLiveData<Rating> by lazy { MutableLiveData<Rating>() }

    fun setExhibitRate(id: Int?, rating: Double) {
        doWork {
            val rating = baseCloudRepository.setExhibitRate(id, rating)
            ratingData.postValue(rating)
        }
    }
}