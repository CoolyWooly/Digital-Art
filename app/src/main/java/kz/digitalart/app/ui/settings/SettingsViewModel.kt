package kz.digitalart.app.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kz.digitalart.app.data.db.PrefsImpl

class SettingsViewModel @ViewModelInject constructor(
    val prefsImpl: PrefsImpl
) : ViewModel() {
    private val TAG = this::class.java.simpleName
}