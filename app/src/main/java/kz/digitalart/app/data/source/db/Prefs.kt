package kz.digitalart.app.data.source.db

interface Prefs {
    fun setLanguage(lang: String)
    fun getLanguage(): String

    fun setRatings(id: Int)
    fun getRatings(): String
}