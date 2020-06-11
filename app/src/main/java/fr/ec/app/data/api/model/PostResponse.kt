package fr.ec.app.data.api.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("name")
    val title: String,
    @SerializedName("tagline")
    val subTitle: String,
    @SerializedName("thumbnail")
    val thumbnail : Thumbnail
)

data class Thumbnail(
    @SerializedName("image_url")
    val imageUrl : String
)