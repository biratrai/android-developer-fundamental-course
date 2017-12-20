package com.example.gooner10.androiddeveloperfundamentals

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_async_loader.*
import org.json.JSONObject

class AsyncLoaderActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_loader)

        //Check if a Loader is running, if it is, reconnect to it
        if (supportLoaderManager.getLoader<Any>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
    }

    /**
     * Gets called when the user pushes the "Search Books" button
     *
     * @param view The view (Button) that was clicked.
     */
    fun searchBooks(view: View) {
        // Get the search string from the input field.
        val queryString = bookInput!!.text.toString()

        // Hide the keyboard when the button is pushed.
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)

        // Check the status of the network connection.
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        // If the network is active and the search field is not empty,
        // add the search term to the arguments Bundle and start the loader.
        if (networkInfo != null && networkInfo.isConnected && queryString.isNotEmpty()) {
            authorText!!.text = ""
            titleText!!.setText(R.string.loading)
            val queryBundle = Bundle()
            queryBundle.putString("queryString", queryString)
            supportLoaderManager.restartLoader(0, queryBundle, this)
        } else {
            if (queryString.isEmpty()) {
                authorText!!.text = ""
                titleText!!.setText(R.string.no_search_term)
            } else {
                authorText!!.text = ""
                titleText!!.setText(R.string.no_network)
            }
        }// Otherwise update the TextView to tell the user there is no connection or no search term.
    }

    /**
     * Loader Callbacks
     */

    /**
     * The LoaderManager calls this method when the loader is created.
     *
     * @param id ID integer to id   entify the instance of the loader.
     * @param args The bundle that contains the search parameter.
     * @return Returns a new BookLoader containing the search term.
     */
    override fun onCreateLoader(id: Int, args: Bundle): Loader<String> {
        return BookLoader(this, args.getString("queryString")!!)
    }

    /**
     * Called when the data has been loaded. Gets the desired information from
     * the JSON and updates the Views.
     *
     * @param loader The loader that has finished.
     * @param data The JSON response from the Books API.
     */
    override fun onLoadFinished(loader: Loader<String>, data: String) {
        try {
            // Convert the response into a JSON object.
            val jsonObject = JSONObject(data)
            // Get the JSONArray of book items.
            val itemsArray = jsonObject.getJSONArray("items")

            // Initialize iterator and results fields.
            var i = 0
            var title: String? = null
            var authors: String? = null

            // Look for results in the items array, exiting when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() || authors == null && title == null) {
                // Get the current item information.
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo")

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title")
                    authors = volumeInfo.getString("authors")
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                // Move to the next item.
                i++
            }

            // If both are found, display the result.
            if (title != null && authors != null) {
                titleText.text = title
                authorText.text = authors
                bookInput.setText("")
            } else {
                // If none are found, update the UI to show failed results.
                titleText!!.setText(R.string.no_results)
                authorText!!.text = ""
            }

        } catch (e: Exception) {
            // If onPostExecute does not receive a proper JSON string, update the UI to show failed results.
            titleText.setText(R.string.no_results)
            authorText.text = ""
            e.printStackTrace()
        }


    }

    /**
     * In this case there are no variables to clean up when the loader is reset.
     *
     * @param loader The loader that was reset.
     */
    override fun onLoaderReset(loader: Loader<String>) {}
}
