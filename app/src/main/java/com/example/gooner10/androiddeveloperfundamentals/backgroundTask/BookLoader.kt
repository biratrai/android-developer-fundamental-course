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

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import android.util.Log
import com.example.gooner10.androiddeveloperfundamentals.backgroundTask.model.Books
import com.example.gooner10.androiddeveloperfundamentals.backgroundTask.model.GoogleBooksApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * AsyncTaskLoader implementation that opens a network connection and
 * query's the Book Service API.
 */
class BookLoader// Constructor providing a reference to the search term.
(context: Context, // Variable that stores the search string.
 private val mQueryString: String) : AsyncTaskLoader<Books>(context) {

    private val BOOK_BASE_URL = "https://www.googleapis.com/books/"
    private val TAG = BookLoader::class.java.simpleName

    /**
     * This method is invoked by the LoaderManager whenever the loader is started
     */
    override fun onStartLoading() {
        forceLoad() // Starts the loadInBackground method
    }

    /**
     * Connects to the network and makes the Books API request on a background thread.
     *
     * @return Returns the raw JSON response from the API call.
     */
    override fun loadInBackground(): Books? {
        val call = apiService().getBookByName(mQueryString, "2", "books")
        val response = call.execute()
        return if (response.isSuccessful) {
            val result = response.body()
            Log.d(TAG, "result: " + result)
            result
        } else {
            Log.d(TAG, "returning empty books: ")
            Books()
        }
    }

    companion object {
        fun apiService() = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GoogleBooksApiService::class.java)
    }
}

