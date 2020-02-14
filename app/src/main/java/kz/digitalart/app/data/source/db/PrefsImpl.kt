package kz.digitalart.app.data.source.db

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import javax.inject.Inject

class PrefsImpl @Inject constructor(appContext: Context) : Prefs {

    private val LANGUAGE = "LANGUAGE"
    private val RATINGS = "RATINGS"

    private val prefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun setLanguage(lang: String) {
        val editor = prefs.edit()
        editor.putString(LANGUAGE, lang)
        editor.apply()
    }

    override fun getLanguage(): String {
        return prefs.getString(LANGUAGE, "ru") ?: "ru"
    }

    override fun setRatings(id: Int) {
        val editor = prefs.edit()
        var def = prefs.getString(RATINGS, "")
        def += " $id"
        editor.putString(RATINGS, def)
        editor.apply()
    }

    override fun getRatings(): String {
        return prefs.getString(RATINGS, "") ?: ""
    }
}