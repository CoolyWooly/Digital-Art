package kz.digitalart.app.ui.about

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kz.digitalart.app.core.BaseViewModel
import kz.digitalart.app.data.source.cloud.BaseCloudRepository
import kz.digitalart.app.data.source.db.PrefsImpl
import kz.digitalart.app.domain.model.AboutModel
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val prefsImpl: PrefsImpl,
    private val baseCloudRepository: BaseCloudRepository
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName
    val data: MutableLiveData<AboutModel> by lazy { MutableLiveData<AboutModel>() }

    init {
        getAbout()
    }

    private fun getAbout() {
        viewModelScope.launch {
            try {
                val exhibits = baseCloudRepository.getAbout(prefsImpl.getLanguage())
                data.postValue(exhibits)
            } catch (e: Exception) {
                Log.e("Coroutine-BaseViewModel", e.toString())
            } finally {
            }
        }
    }
}