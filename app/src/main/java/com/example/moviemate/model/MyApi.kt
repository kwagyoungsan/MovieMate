package com.example.moviemate.model

import com.example.moviemate.model.response.DailyResponse
import com.example.moviemate.model.response.MovieDetailResponse
import com.example.moviemate.model.response.SearchMovieResponse
import com.example.moviemate.model.response.WeeklyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("searchDailyBoxOfficeList.json")
    fun getDailyBoxOffice(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: String
    ): Call<DailyResponse>

    @GET("searchWeeklyBoxOfficeList.json")
    fun getWeeklyBoxOffice(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: String,
        @Query("weekGb") weekGb: String = "0"
    ): Call<WeeklyResponse>

    @GET("searchMovieInfo.json")
    fun getMovieDetail(
        @Query("key") apiKey: String,
        @Query("movieCd") movieCd: String
    ): Call<MovieDetailResponse>

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