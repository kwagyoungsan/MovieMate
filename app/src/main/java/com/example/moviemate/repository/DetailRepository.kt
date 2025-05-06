package com.example.moviemate.repository

import com.example.moviemate.view.MovieDetail

interface DetailRepository {
    fun fetchMovieDetail(movieCd: String, onResult: (Result<MovieDetail>) -> Unit)
}