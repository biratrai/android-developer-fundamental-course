package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class IndustryIdentifier {

    @SerializedName("identifier")
    var identifier: String? = null
    @SerializedName("type")
    var type: String? = null

}
