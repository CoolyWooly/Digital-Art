package kz.digitalart.app.ui.home

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.cloud.ResultWrapper
import kz.digitalart.app.data.cloud.repository.BaseCloudRepository
import kz.digitalart.app.data.db.PrefsImpl
import kz.digitalart.app.domain.model.ExhibitModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefsImpl: PrefsImpl,
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName
    val isRefreshing: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val exhibitsData: MutableLiveData<List<ExhibitModel>> by lazy { MutableLiveData<List<ExhibitModel>>() }
    val error: MutableLiveData<ResultWrapper.Error> by lazy { MutableLiveData<ResultWrapper.Error>() }
    val searchString: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        getExhibits(page = 1, limit = 20)
    }

    fun getExhibits(page: Int, limit: Int, searchString: String? = null) {
        launchIO {
            isRefreshing.postValue(true)
            val exhibits = baseCloudRepository.getExhibits(
                page,
                limit,
                searchString,
                prefsImpl.getLanguage(),
                "desc"
            )
            when (exhibits) {
                is ResultWrapper.Error -> error.postValue(exhibits)
                is ResultWrapper.Success -> exhibitsData.postValue(exhibits.value)
            }
            isRefreshing.postValue(false)
        }
    }
}