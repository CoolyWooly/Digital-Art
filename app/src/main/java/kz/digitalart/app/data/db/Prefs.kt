package kz.digitalart.app.data.db

interface Prefs {

    fun setLanguage(lang: String)
    fun getLanguage(): String

    fun setRatings(id: Int)
    fun getRatings(): String
}