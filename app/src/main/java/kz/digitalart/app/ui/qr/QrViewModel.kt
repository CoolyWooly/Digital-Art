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
        if (id == 2) {
            val exhibit = Exhibit(2, 1, 4.5, "Колосс", "описание2", "1992", "Ерлан", null, null)
            exhibitsData.postValue(exhibit)
        } else {
            val exhibit = Exhibit(5, 1, 3.5, "Башня", "описание5", "1995", "Аслан", null, null)
            exhibitsData.postValue(exhibit)
        }

//        doWork {
//            val exhibits = baseCloudRepository.getExhibit(id, "kk")
//            exhibitsData.postValue(exhibits)
//        }

        exhibitsData = MutableLiveData()
    }
}