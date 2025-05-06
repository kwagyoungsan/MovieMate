package com.example.moviemate.model

import com.example.moviemate.model.response.DailyResponse
import com.example.moviemate.model.response.MovieDetailResponse
import com.example.moviemate.model.response.SearchMovieResponse
import com.example.moviemate.model.response.WeeklyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("boxoffice/searchDailyBoxOfficeList.json")
    fun getDailyBoxOffice(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: Int
    ): Call<DailyResponse>

    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    fun getWeeklyBoxOffice(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDt: Int,
        @Query("weekGb") weekGb: Int = 0
    ): Call<WeeklyResponse>

    @GET("movie/searchMovieInfo.json")
    fun getMovieDetail(
        @Query("key") apiKey: String,
        @Query("movieCd") movieCd: String
    ): Call<MovieDetailResponse>

    @GET("movie/searchMovieList.json")
    fun searchMovieListByMovieNm(
        @Query("key") apiKey: String,
        @Query("movieNm") movieNm: String
    ): Call<SearchMovieResponse>

    @GET("movie/searchMovieList.json")
    fun searchMovieListByDirectorNm(
        @Query("key") apiKey: String,
        @Query("directorNm") directorNm: String
    ): Call<SearchMovieResponse>
}