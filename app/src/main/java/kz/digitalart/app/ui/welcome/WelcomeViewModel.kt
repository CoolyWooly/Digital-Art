package kz.digitalart.app.ui.welcome

import androidx.lifecycle.ViewModel
import kz.digitalart.app.data.source.db.PrefsImpl
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    val prefsImpl: PrefsImpl
) : ViewModel()