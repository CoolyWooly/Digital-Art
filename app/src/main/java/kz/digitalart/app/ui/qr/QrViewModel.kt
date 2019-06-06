package kz.digitalart.app.ui.qr

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.domain.model.Exhibit
import javax.inject.Inject

class QrViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName
    var exhibitsData: MutableLiveData<Exhibit> = MutableLiveData()

    fun getExhibit(id: Int?) {

        doWork {
            val exhibits = baseCloudRepository.getExhibit(id, "kk")
            exhibitsData.postValue(exhibits)
        }

        exhibitsData = MutableLiveData()
    }
}