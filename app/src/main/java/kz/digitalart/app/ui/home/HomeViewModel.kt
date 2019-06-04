package kz.digitalart.app.ui.home

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.model.response.ErrorModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefsImpl: PrefsImpl,
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName
    val exhibitsData: MutableLiveData<List<Exhibit>> by lazy { MutableLiveData<List<Exhibit>>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val searchString: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        getExhibits(0, 20, null)
    }

    fun getExhibits(page: Int?, limit: Int?, searchString: String?) {
        doWork {
            val exhibits =
                baseCloudRepository.getExhibits(page, limit, searchString, prefsImpl.getLanguage())
            exhibitsData.postValue(exhibits)
        }
    }
}