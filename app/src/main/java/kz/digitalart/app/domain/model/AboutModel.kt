package kz.digitalart.app.domain.model

import com.google.gson.annotations.SerializedName

data class AboutModel(
    @SerializedName("info") val info: String?
)