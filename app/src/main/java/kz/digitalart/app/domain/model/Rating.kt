package kz.digitalart.app.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("rating") val rating: Double?
) : Serializable