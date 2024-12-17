package com.example.moviemate.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("searchDailyBoxOfficeList.json")
    fun getTargetDt(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: Int
    ): Call<BoxOfficeResponse>
}