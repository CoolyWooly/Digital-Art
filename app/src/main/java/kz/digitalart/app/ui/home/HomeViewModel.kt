package kz.digitalart.app.ui.home

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.response.ErrorModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefsImpl: PrefsImpl,
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName
    val exhibitsData: MutableLiveData<List<ExhibitModel>> by lazy { MutableLiveData<List<ExhibitModel>>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val searchString: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getExhibits(page: Int?, limit: Int?, searchString: String?) {
        doWork {
            val exhibits = baseCloudRepository.getExhibits(
                page,
                limit,
                searchString,
                prefsImpl.getLanguage(),
                "desc"
            )
            exhibitsData.postValue(exhibits)
        }
    }
}