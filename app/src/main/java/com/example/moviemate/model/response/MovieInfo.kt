package com.example.moviemate.model.response

data class MovieInfo(
    val movieCd: String,
    val movieNm: String,
    val prdtYear: String,
    val openDt: String,
    val showTm: String,
    val prdtStatNm: String,
    val genres: List<Genre>,
    val directors: List<Director>?,
    val actors: List<Actor>?,
    val audits: List<Audit>,
    val nations: List<Nation>
)

data class Genre(
    val genreNm: String
)

data class Audit(
    val watchGradeNm: String
)
