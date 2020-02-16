package kz.digitalart.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExhibitModel(
    @SerializedName("id_exhibit") val id_exhibit: Int?,
    @SerializedName("museum_id") val museum_id: Int?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("year") val year: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("images") val images: List<ImageModel>?,
    @SerializedName("audio") val audio: String?
) : Serializable