package com.example.gooner10.androiddeveloperfundamentals.backgroundTask.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Service
 */
interface GoogleBooksApiService {
    @GET("v1/volumes?")
    fun getBookByName(@Query("q") bookName: String,
                      @Query("maxResults") maxResult: String,
                      @Query("printType") printType: String)
            : Call<Books>
}