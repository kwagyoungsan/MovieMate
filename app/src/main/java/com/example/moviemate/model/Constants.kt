package com.example.moviemate.model

import com.example.moviemate.BuildConfig

object API {
    const val apiKey = BuildConfig.API_KEY

    const val BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/soap/boxoffice.json?key="
    const val BASE_URL_KEY = "${BASE_URL}${apiKey}"
}

object Constants {
    const val MOVIE_RANK = "Movie Rank"
    const val SEARCH = "Search"
}