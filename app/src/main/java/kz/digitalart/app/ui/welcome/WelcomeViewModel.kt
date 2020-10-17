package kz.digitalart.app.ui.welcome

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kz.digitalart.app.data.db.PrefsImpl

class WelcomeViewModel @ViewModelInject constructor(
    val prefsImpl: PrefsImpl
) : ViewModel()