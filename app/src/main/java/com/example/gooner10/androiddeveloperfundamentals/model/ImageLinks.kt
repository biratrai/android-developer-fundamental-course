package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class ImageLinks {

    @SerializedName("smallThumbnail")
    var smallThumbnail: String? = null
    @SerializedName("thumbnail")
    var thumbnail: String? = null

}
