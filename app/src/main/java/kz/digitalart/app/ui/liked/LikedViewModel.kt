package kz.digitalart.app.ui.liked

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.model.response.ErrorModel
import javax.inject.Inject

class LikedViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName
    val exhibitsData: MutableLiveData<List<Exhibit>> by lazy { MutableLiveData<List<Exhibit>>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }

    init {
        getExhibits(null)
    }

    private fun getExhibits(searchString: String?) {
        doWork {
            val exhibits = baseCloudRepository.getPopular(null, null, searchString, "kk")
            exhibitsData.postValue(exhibits)
        }
    }
}