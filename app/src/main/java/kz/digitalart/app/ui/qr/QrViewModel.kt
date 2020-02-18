package kz.digitalart.app.ui.qr

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.cloud.ResultWrapper
import kz.digitalart.app.data.cloud.repository.BaseCloudRepository
import kz.digitalart.app.data.db.PrefsImpl
import kz.digitalart.app.domain.model.ExhibitModel
import javax.inject.Inject

class QrViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository,
    private val prefsImpl: PrefsImpl
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName
    var exhibitsData: MutableLiveData<ExhibitModel> = MutableLiveData()
    val error: MutableLiveData<ResultWrapper.Error> by lazy { MutableLiveData<ResultWrapper.Error>() }

    fun getExhibit(id: Int?) {
        launchIO {
            when (val exhibits = baseCloudRepository.getExhibit(id, prefsImpl.getLanguage())) {
                is ResultWrapper.Error -> error.postValue(exhibits)
                is ResultWrapper.Success -> {
                    exhibitsData.postValue(exhibits.value)
                    exhibitsData = MutableLiveData()
                }
            }
        }
    }
}