package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class ReadingModes {

    @SerializedName("image")
    var image: Boolean? = null
    @SerializedName("text")
    var text: Boolean? = null

}
