package com.example.gooner10.androiddeveloperfundamentals.model

import com.google.gson.annotations.SerializedName

class AccessInfo {

    @SerializedName("accessViewStatus")
    var accessViewStatus: String? = null
    @SerializedName("country")
    var country: String? = null
    @SerializedName("embeddable")
    var embeddable: Boolean? = null
    @SerializedName("epub")
    var epub: com.example.gooner10.androiddeveloperfundamentals.model.Epub? = null
    @SerializedName("pdf")
    var pdf: com.example.gooner10.androiddeveloperfundamentals.model.Pdf? = null
    @SerializedName("publicDomain")
    var publicDomain: Boolean? = null
    @SerializedName("quoteSharingAllowed")
    var quoteSharingAllowed: Boolean? = null
    @SerializedName("textToSpeechPermission")
    var textToSpeechPermission: String? = null
    @SerializedName("viewability")
    var viewability: String? = null
    @SerializedName("webReaderLink")
    var webReaderLink: String? = null

}
