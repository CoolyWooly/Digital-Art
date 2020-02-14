package kz.digitalart.app.ui.qr

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.ExhibitModel
import javax.inject.Inject

class QrViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository,
    private val prefsImpl: PrefsImpl
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName
    var exhibitsData: MutableLiveData<ExhibitModel> = MutableLiveData()

    fun getExhibit(id: Int?) {
        doWork {
            val exhibits = baseCloudRepository.getExhibit(id, prefsImpl.getLanguage())
            exhibitsData.postValue(exhibits)
            exhibitsData = MutableLiveData()
        }
    }
}