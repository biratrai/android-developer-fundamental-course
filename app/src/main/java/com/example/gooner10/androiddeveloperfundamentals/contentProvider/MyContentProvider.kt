package com.example.gooner10.androiddeveloperfundamentals.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.R


class MyContentProvider : ContentProvider() {
    private val TAG = MyContentProvider::class.java.simpleName
    private var data = arrayOf(String())

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString())
        return 0
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
            0 -> return ProviderContract.MULTIPlE_RECORD_MIME_TYPE
            1 -> return ProviderContract.SINGLE_RECORD_MIME_TYPE
            else ->
                // Alternatively, throw an exception.
                return null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString())
        return null
    }

    override fun onCreate(): Boolean {
        initializeUriMatching()
        val context = context
        data = context!!.resources.getStringArray(R.array.words)
        return true
    }

    private fun initializeUriMatching() {
        uriMatcher.addURI(ProviderContract.AUTHORITY, ProviderContract.CONTENT_PATH + "/#", 1)

        // Matches a URI that is just the authority + the path, triggering the return of all words.
        uriMatcher.addURI(ProviderContract.AUTHORITY, ProviderContract.CONTENT_PATH, 0)
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        var id: Int

        when (uriMatcher.match(uri)) {
            0 -> {
                // Matches URI to get all of the entries.
                id = ProviderContract.ALL_ITEMS
                // Look at the remaining arguments to see whether there are constraints.
                // In this example, we only support getting a specific entry by id. Not full search.
                // For a real-life app, you need error-catching code; here we assume that the
                // value we need is actually in selectionArgs and valid.
                if (selection != null) {
                    id = Integer.parseInt(selectionArgs!![0])
                }
            }

            1 ->
                // The URI ends in a numeric value, which represents an id.
                // Parse the URI to extract the value of the last, numeric part of the path,
                // and set the id to that value.
                id = Integer.parseInt(uri.lastPathSegment)

            UriMatcher.NO_MATCH -> {
                // You should do some error handling here.
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME.")
                id = -1
            }
            else -> {
                // You should do some error handling here.
                Log.d(TAG, "INVALID URI - URI NOT RECOGNZED.")
                id = -1
            }
        }// With a database, you would then use this value and the path to build a query.
        Log.d(TAG, "query: " + id)
        return populateCursor(id)
    }

    private fun populateCursor(id: Int): Cursor {
        val cursor = MatrixCursor(arrayOf(ProviderContract.CONTENT_PATH))

        // If there is a valid query, execute it and add the result to the cursor.
        if (id == ProviderContract.ALL_ITEMS) {
            for (i in data.indices) {
                val word = data[i]
                cursor.addRow(arrayOf<Any>(word))
            }
        } else if (id >= 0) {
            // Execute the query to get the requested word.
            val word = data[id]
            // Add the result to the cursor.
            cursor.addRow(arrayOf<Any>(word))
        }
        return cursor
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString())
        return 0
    }

    companion object {

        // UriMatcher is a helper class for processing the accepted Uri schemes
        // for this content provider.
        // https://developer.android.com/reference/android/content/UriMatcher.html
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }
}
