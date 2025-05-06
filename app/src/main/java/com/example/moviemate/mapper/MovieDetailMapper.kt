package com.example.moviemate.mapper

import com.example.moviemate.model.response.MovieInfo
import com.example.moviemate.view.MovieDetail


fun MovieInfo.toDomain(): MovieDetail = MovieDetail(
    movieCd = this.movieCd,
    movieNm = this.movieNm,
    prdtYear = this.prdtYear,
    openDt = this.openDt,
    showTm = this.showTm,
    prdtStatNm = this.prdtStatNm,
    genreNm = this.genres.joinToString { it.genreNm },
    watchGradeNm = this.audits.firstOrNull()?.watchGradeNm ?: "등급 정보 없음",
    directors = this.directors ?: emptyList(),
    actors = this.actors ?: emptyList(),
    nations = this.nations ?: emptyList(),
    cast = this.actors?.joinToString { "${it.peopleNm}(${it.cast ?: "?"})" }
)