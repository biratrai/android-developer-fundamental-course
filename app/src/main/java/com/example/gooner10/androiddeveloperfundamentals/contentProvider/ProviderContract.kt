package com.example.gooner10.androiddeveloperfundamentals.contentProvider

import android.net.Uri

/**
 * Contract Class for ContentProvider
 */
object ProviderContract {
    val AUTHORITY = "com.example.gooner10.contentprovider"
    val CONTENT_PATH = "words"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
    val ALL_ITEMS = -2;
    val WORD_ID = "id"
    val SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words"
    val MULTIPlE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.provider.words"

}