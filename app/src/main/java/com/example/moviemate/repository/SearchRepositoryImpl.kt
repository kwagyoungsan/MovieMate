package com.example.moviemate.repository

import android.util.Log
import com.example.moviemate.BuildConfig
import com.example.moviemate.mapper.toResult
import com.example.moviemate.mapper.toSearchResult
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.response.Movie
import com.example.moviemate.model.response.SearchMovieResponse
import com.example.moviemate.view.MovieSearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: MyApi
) : SearchRepository {

    override fun searchByMovieNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit) {
        api.searchMovieListByMovieNm(BuildConfig.API_KEY, query)?.enqueue(object : Callback<SearchMovieResponse> {
            override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                if (response.isSuccessful) {
                    val results = response.body()?.movieListResult?.movieList?.map { it.toResult() } ?: emptyList()
                    onResult(Result.success(results))
                } else {
                    onResult(Result.failure(Exception("검색 실패: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                onResult(Result.failure(t))
            }
        })
    }

    override fun searchByDirectorNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit) {
        api.searchMovieListByDirectorNm(BuildConfig.API_KEY, query)?.enqueue(object : Callback<SearchMovieResponse> {
            override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                if (response.isSuccessful) {
                    val results = response.body()?.movieListResult?.movieList?.map { it.toResult() } ?: emptyList()
                    onResult(Result.success(results))
                } else {
                    onResult(Result.failure(Exception("검색 실패: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                onResult(Result.failure(t))
            }
        })
    }
}