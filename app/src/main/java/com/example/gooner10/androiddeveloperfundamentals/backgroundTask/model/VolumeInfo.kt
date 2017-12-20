package com.example.gooner10.androiddeveloperfundamentals.backgroundTask.model

import com.google.gson.annotations.SerializedName

class VolumeInfo {

    @SerializedName("allowAnonLogging")
    var allowAnonLogging: Boolean? = null
    @SerializedName("authors")
    var authors: List<String>? = null
    @SerializedName("averageRating")
    var averageRating: Long? = null
    @SerializedName("canonicalVolumeLink")
    var canonicalVolumeLink: String? = null
    @SerializedName("categories")
    var categories: List<String>? = null
    @SerializedName("contentVersion")
    var contentVersion: String? = null
    @SerializedName("imageLinks")
    var imageLinks: ImageLinks? = null
    @SerializedName("industryIdentifiers")
    var industryIdentifiers: List<IndustryIdentifier>? = null
    @SerializedName("infoLink")
    var infoLink: String? = null
    @SerializedName("language")
    var language: String? = null
    @SerializedName("maturityRating")
    var maturityRating: String? = null
    @SerializedName("pageCount")
    var pageCount: Long? = null
    @SerializedName("previewLink")
    var previewLink: String? = null
    @SerializedName("printType")
    var printType: String? = null
    @SerializedName("publishedDate")
    var publishedDate: String? = null
    @SerializedName("ratingsCount")
    var ratingsCount: Long? = null
    @SerializedName("readingModes")
    var readingModes: ReadingModes? = null
    @SerializedName("title")
    var title: String? = null

}
