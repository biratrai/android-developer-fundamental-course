package com.example.gooner10.androiddeveloperfundamentals.backgroundTask.model

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("accessInfo")
    var accessInfo: AccessInfo? = null
    @SerializedName("etag")
    var etag: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("kind")
    var kind: String? = null
    @SerializedName("saleInfo")
    var saleInfo: SaleInfo? = null
    @SerializedName("selfLink")
    var selfLink: String? = null
    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfo? = null

}
