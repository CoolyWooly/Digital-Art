package kz.digitalart.app.ui.settings

import androidx.lifecycle.ViewModel
import kz.digitalart.app.data.source.db.PrefsImpl
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    val prefsImpl: PrefsImpl
) : ViewModel() {
    private val TAG = this::class.java.simpleName
}