package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class Epub {

    @SerializedName("downloadLink")
    var downloadLink: String? = null
    @SerializedName("isAvailable")
    var isAvailable: Boolean? = null

}
