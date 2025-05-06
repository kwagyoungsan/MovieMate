package com.example.moviemate.view

import com.example.moviemate.model.response.Actor
import com.example.moviemate.model.response.Director

data class MovieSearchResult(
    val movieCd: String,
    val movieNm: String,
    val prdtYear: String,
    val openDt: String,
    val typeNm: String,
    val directors: List<Director>,
    val actors: List<Actor>
)