package com.example.moviemate.util

import com.example.moviemate.BuildConfig

object API {
    const val apiKey = BuildConfig.API_KEY

    const val BASE_URL_DAILY = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
    const val BASE_URL_WEEKLY = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key="

    const val BASE_URL_KEY_DAILY = "$BASE_URL_DAILY$apiKey"
    const val BASE_URL_KEY_WEEKLY = "$BASE_URL_WEEKLY$apiKey"
}

object Constants {
    const val MOVIE_RANK = "Movie Rank"
    const val SEARCH = "Search"
    val ranges = listOf("0~50만", "50~100만", "100~500만", "500~1000만", "1000만 이상")
}