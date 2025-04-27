package com.example.moviemate.model

import com.example.moviemate.model.response.DailyBoxOfficeResponse
import com.example.moviemate.model.response.DetailInfoResponse
import com.example.moviemate.model.response.SearchMovieResponse
import com.example.moviemate.model.response.WeeklyBoxOfficeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("searchDailyBoxOfficeList.json")
    fun getTargetDt(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: Int
    ): Call<DailyBoxOfficeResponse>

    @GET("searchWeeklyBoxOfficeList.json")
    fun getRangeDt(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: Int,
        @Query("weekGb") weekGb: Int
    ): Call<WeeklyBoxOfficeResponse>

    @GET("searchMovieInfo.json")
    fun getDetailInfo(
        @Query("key") apiKey: String,
        @Query("movieCd") movieCd: Int,
    ): Call<DetailInfoResponse>

    @GET("searchMovieList.json")
    fun searchMovieListByMovieNm(
        @Query("key") apiKey: String,
        @Query("movieNm") movieNm: String
    ): Call<SearchMovieResponse>

    @GET("searchMovieList.json")
    fun searchMovieListByDirectorNm(
        @Query("key") apiKey: String,
        @Query("directorNm") directorNm: String
    ): Call<SearchMovieResponse>

}