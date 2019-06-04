package kz.digitalart.app.ui.about

import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.domain.model.About
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName
    val data: MutableLiveData<About> by lazy { MutableLiveData<About>() }

    init {
        getAbout()
    }

    private fun getAbout() {
        doWork {
            val exhibits = baseCloudRepository.getAbout()
            data.postValue(exhibits)
        }
    }
}