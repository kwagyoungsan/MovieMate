package com.example.moviemate.model.response

data class SearchMovieResponse(
    val movieListResult: MovieListResult
)

data class MovieListResult(
    val movieList: List<Movie>
)

data class Movie(
    val movieCd: String,
    val movieNm: String,
    val prdtYear: String,
    val openDt: String,
    val typeNm: String,
    val directors: List<Director>,
    val actors: List<Actor>
)
