package com.example.moviemate.repository

import com.example.moviemate.view.MovieSearchResult

interface MovieRepository {
    fun searchByMovieNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit)
    fun searchByDirectorNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit)
}