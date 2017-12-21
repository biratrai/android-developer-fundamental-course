package com.example.gooner10.androiddeveloperfundamentals.rxKotlin

import com.example.gooner10.androiddeveloperfundamentals.backgroundTask.model.Books
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Service
 */
interface RxBooksApiService {
    @GET("v1/volumes?")
    fun getBookByName(@Query("q") bookName: String,
                      @Query("maxResults") maxResult: String,
                      @Query("printType") printType: String)
            : Observable<Books>
}