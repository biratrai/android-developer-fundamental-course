package com.example.gooner10.androiddeveloperfundamentals.contentProvider

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.gooner10.androiddeveloperfundamentals.R
import kotlinx.android.synthetic.main.activity_content_provider.*

class ContentProviderActivity : AppCompatActivity() {
    val TAG = ContentProviderActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
    }

    fun displayContentProviderValues(view: View) {
        // URI That identifies the content provider and the table.
        val queryUri = ProviderContract.CONTENT_URI.toString()

        // The columns to return for each row. Setting this to null returns all of them.
        // When there is only one column, as in the case of this example, setting this
        // explicitly is optional, but can be helpful for documentation purposes.
        val projection = arrayOf(ProviderContract.CONTENT_PATH) // Only get words.

        // Argument clause for the selection criteria for which rows to return.
        // Formatted as an SQL WHERE clause (excluding the WHERE itself).
        // Passing null returns all rows for the given URI.
        val selectionClause: String?

        // Argument values for the selection criteria.
        // If you include ?s in selection, they are replaced by values from selectionArgs,
        // in the order that they appear.
        // IMPORTANT: It is a best security practice to always separate selection and selectionArgs.
        val selectionArgs: Array<String>?

        // The order in which to sort the results.
        // Formatted as an SQL ORDER BY clause (excluding the ORDER BY keyword).
        // Usually ASC or DESC; null requests the default sort order, which could be unordered.
        val sortOrder: String? = null // For this example, we accept the order returned by the response.

        // Set selection criteria depending on which button was pressed.
        when (view.id) {
            R.id.button_display_all -> {
                selectionClause = null
                selectionArgs = null
            }
            R.id.button_display_first -> {
                selectionClause = ProviderContract.WORD_ID + " = ?"
                selectionArgs = arrayOf("0")
            }
            else -> {
                selectionClause = null
                selectionArgs = null
            }
        }

        // Let the content resolver parse the query and do the right things with it.
        // If you provide a well-formed query, the results should always be civilized.
        // This is magic that is explained in the next practical.
        // We don't need to create a custom content resolver,
        // ...we just use the one already there for our app context.
        val cursor = contentResolver.query(Uri.parse(queryUri), projection, selectionClause,
                selectionArgs, sortOrder)

        // If we got data back, display it, otherwise report the error.
        // See WordList app and database chapter for more on cursors.
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(projection[0])
                do {
                    val word = cursor.getString(columnIndex)
                    providerTextview?.append(word + "\n")
                } while (cursor.moveToNext())
            } else {
                Log.d(TAG, "onClickDisplayEntries " + "No data returned.")
                providerTextview?.append("No data returned." + "\n")
            }
            cursor.close()
        } else {
            Log.d(TAG, "onClickDisplayEntries " + "Cursor is null.")
            providerTextview?.append("Cursor is null." + "\n")
        }
    }
}
