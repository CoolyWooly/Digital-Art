package kz.digitalart.app.domain.model

import com.google.gson.annotations.SerializedName

data class About(
        @SerializedName("description") val description: String?
)