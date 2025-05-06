package com.example.moviemate.model.response

data class MovieDetailResponse(
    val movieInfoResult: MovieInfoResult
)

data class MovieInfoResult(
    val movieInfo: MovieInfo
)