package com.example.moviemate.view

import com.example.moviemate.model.response.Actor
import com.example.moviemate.model.response.Director
import com.example.moviemate.model.response.Nation

data class MovieDetail(
    val movieCd: String,
    val movieNm: String,
    val prdtYear: String,
    val openDt: String,
    val showTm: String,
    val prdtStatNm: String,
    val genreNm: String,
    val watchGradeNm: String,
    val directors: List<Director>,
    val actors: List<Actor>,
    val nations: List<Nation>,
    val cast: String?
)