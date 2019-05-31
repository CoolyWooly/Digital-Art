package kz.digitalart.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Exhibit(
    @SerializedName("name") val name: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("year") val year: String? = "",
    @SerializedName("author") val author: String? = "",
    @SerializedName("rating") val rating: Double? = 0.0,
    @SerializedName("photos") val photos: ArrayList<String>? = ArrayList(),
    @SerializedName("audio") val audio: String? = ""
) : Serializable