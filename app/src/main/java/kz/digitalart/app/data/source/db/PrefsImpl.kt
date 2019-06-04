package kz.digitalart.app.data.source.db

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PrefsImpl @Inject constructor(appContext: Context) : Prefs {

    private val LANGUAGE = "LANGUAGE"

    private val fileName = "DigitalArtShrdPref"

    private val prefs: SharedPreferences =
        appContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    override fun setLanguage(lang: String) {
        val editor = prefs.edit()
        editor.putString(LANGUAGE, lang)
        editor.commit()
    }

    override fun getLanguage(): String {
        return prefs.getString(LANGUAGE, "ru")
    }
}