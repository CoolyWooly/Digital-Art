package kz.digitalart.app.data.source.db

interface Prefs {
    fun setLanguage(lang: String)
    fun getLanguage(): String
}