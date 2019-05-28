package kz.digitalart.app.ui.welcome

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WelcomeViewModel @Inject constructor() : ViewModel() {
    val action: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun onClickStart(view: View) {
        action.value = "click!"
    }
}