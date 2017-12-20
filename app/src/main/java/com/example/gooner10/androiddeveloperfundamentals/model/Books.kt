package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class Books {

    @SerializedName("items")
    var items: List<Item>? = null
    @SerializedName("kind")
    var kind: String? = null
    @SerializedName("totalItems")
    var totalItems: Long? = null

}
