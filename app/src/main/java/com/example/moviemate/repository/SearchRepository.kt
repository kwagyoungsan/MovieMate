package com.example.moviemate.repository

import com.example.moviemate.view.MovieSearchResult

interface SearchRepository {
    fun searchByMovieNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit)
    fun searchByDirectorNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit)
}