package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("accessInfo")
    var accessInfo: com.example.gooner10.androiddeveloperfundamentals.model.AccessInfo? = null
    @SerializedName("etag")
    var etag: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("kind")
    var kind: String? = null
    @SerializedName("saleInfo")
    var saleInfo: com.example.gooner10.androiddeveloperfundamentals.model.SaleInfo? = null
    @SerializedName("selfLink")
    var selfLink: String? = null
    @SerializedName("volumeInfo")
    var volumeInfo: com.example.gooner10.androiddeveloperfundamentals.model.VolumeInfo? = null

}
