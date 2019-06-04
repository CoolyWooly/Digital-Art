package kz.digitalart.app.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

fun updateResources(context: Context?, language: String) {
    val resources = context?.resources
    val dm = resources?.displayMetrics
    val configuration = resources?.configuration
    configuration?.setLocale(Locale(language.toLowerCase()))
    resources?.updateConfiguration(configuration, dm)

    val configuration2 = Configuration(Resources.getSystem().configuration)
    configuration2.setLocale(Locale(language.toLowerCase()))
    Locale.setDefault(Locale(language.toLowerCase()))
    Resources.getSystem().updateConfiguration(configuration2, dm)
}