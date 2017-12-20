/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.gooner10.androiddeveloperfundamentals.backgroundTask


import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


/**
 * Utility class for using the Google Book Search API to download book information.
 */
internal object NetworkUtils {

    private val BOOK_BASE_URL = "https://www.googleapis.com/books/"
    private val QUERY_PARAM = "q" // Parameter for the search string.
    private val MAX_RESULTS = "maxResults" // Parameter that limits search results.
    private val PRINT_TYPE = "printType" // Parameter to filter by print type.

    // Class name for Log tag.
    private val LOG_TAG = NetworkUtils::class.java.simpleName

    /**
     * Method for downloading book information from the Books API based on a search term.
     * This method makes a network call so it can not be called on the main thread.
     *
     * @param queryString The search term for the Books API query
     * @return The raw response from the API as a JSON String
     */
    fun getBookInfo(queryString: String): String? {

        // Set up variables for the try block that need to be closed in the finally block.
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        var bookJSONString: String? = null

        // Attempt to query the Books API.
        try {
            // Base URI for the Books API.


            // Build up your query URI, limiting results to 10 items and printed books.
            val builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build()

            val requestURL = URL(builtURI.toString())

            // Open the network connection.
            urlConnection = requestURL.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            // Get the InputStream.
            val inputStream = urlConnection.inputStream

            // Read the response string into a StringBuilder.
            val builder = StringBuilder()

            reader = BufferedReader(InputStreamReader(inputStream))

            var line: String
            while (reader.readLine() != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // but it does make debugging a *lot* easier if you print out the completed buffer for debugging.
                line = reader.readLine()
                builder.append(line + "\n")
            }

            if (builder.isEmpty()) {
                // Stream was empty.  No point in parsing.
                // return null;
                return null
            }
            bookJSONString = builder.toString()

            // Catch errors.
        } catch (e: IOException) {
            Log.e(LOG_TAG, e.toString())

            // Close the connections.
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    Log.e(LOG_TAG, e.toString())
                }

            }
        }
        Log.d(LOG_TAG, bookJSONString)
        // Return the raw response.
        return bookJSONString
    }

}
