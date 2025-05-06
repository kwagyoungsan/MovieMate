package com.example.moviemate.mapper

import com.example.moviemate.model.response.Movie
import com.example.moviemate.view.MovieSearchResult

fun Movie.toSearchResult(): MovieSearchResult = MovieSearchResult(
    movieCd = this.movieCd,
    movieNm = this.movieNm,
    prdtYear = this.prdtYear,
    openDt = this.openDt,
    typeNm = this.typeNm,
    directors = this.directors ?: emptyList(),
    actors = this.actors ?: emptyList()
)