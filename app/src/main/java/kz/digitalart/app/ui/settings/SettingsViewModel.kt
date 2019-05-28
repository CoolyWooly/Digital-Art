package kz.digitalart.app.ui.settings

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel() {
    private val TAG = this::class.java.simpleName

    companion object {
        val ACTION_LANGUAGE = "ACTION_LANGUAGE"
        val ACTION_LIKED = "ACTION_LIKED"
        val ACTION_ABOUT = "ACTION_ABOUT"
    }
}