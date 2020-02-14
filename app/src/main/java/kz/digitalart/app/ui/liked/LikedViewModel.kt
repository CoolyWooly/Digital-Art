package kz.digitalart.app.ui.liked

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.response.ErrorModel
import javax.inject.Inject

class LikedViewModel @Inject constructor(
    private val prefsImpl: PrefsImpl,
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName
    val exhibitsData: MutableLiveData<List<ExhibitModel>> by lazy { MutableLiveData<List<ExhibitModel>>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }

    init {
        getExhibits(0, 20, null)
    }

    fun getExhibits(page: Int?, limit: Int?, searchString: String?) {
        doWork {
            val exhibits = baseCloudRepository.getPopular(
                page,
                limit,
                searchString,
                prefsImpl.getLanguage()
            )
            exhibitsData.postValue(exhibits)
        }
    }
}