package kz.digitalart.app.ui.about

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.cloud.ResultWrapper
import kz.digitalart.app.data.cloud.repository.BaseCloudRepository
import kz.digitalart.app.data.db.PrefsImpl
import kz.digitalart.app.domain.model.AboutModel

class AboutViewModel @ViewModelInject constructor(
    private val prefsImpl: PrefsImpl,
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName
    val data: MutableLiveData<AboutModel> by lazy { MutableLiveData<AboutModel>() }
    val error: MutableLiveData<ResultWrapper.Error> by lazy { MutableLiveData<ResultWrapper.Error>() }

    init {
        getAbout()
    }

    private fun getAbout() {
        launchIO {
            when (val aboutModel = baseCloudRepository.getAbout(prefsImpl.getLanguage())) {
                is ResultWrapper.Error -> error.postValue(aboutModel)
                is ResultWrapper.Success -> data.postValue(aboutModel.value)
            }
        }
    }
}