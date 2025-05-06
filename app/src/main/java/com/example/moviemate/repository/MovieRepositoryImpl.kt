package com.example.moviemate.repository

import com.example.moviemate.BuildConfig
import com.example.moviemate.mapper.toResult
import com.example.moviemate.view.MovieSearchResult
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.response.SearchMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val api: MyApi) : MovieRepository {

    override fun searchByMovieNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit) {
        api.searchMovieListByMovieNm(BuildConfig.API_KEY, query)
            ?.enqueue(object : Callback<SearchMovieResponse> {
                override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                    if (response.isSuccessful) {
                        val movies = response.body()?.movieListResult?.movieList ?: emptyList()
                        onResult(Result.success(movies.map { it.toResult() }))
                    } else {
                        onResult(Result.failure(Exception("검색 실패")))
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    onResult(Result.failure(t))
                }
            })
    }

    override fun searchByDirectorNm(query: String, onResult: (Result<List<MovieSearchResult>>) -> Unit) {
        api.searchMovieListByDirectorNm(BuildConfig.API_KEY, query)
            ?.enqueue(object : Callback<SearchMovieResponse> {
                override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                    if (response.isSuccessful) {
                        val movies = response.body()?.movieListResult?.movieList ?: emptyList()
                        onResult(Result.success(movies.map { it.toResult() }))
                    } else {
                        onResult(Result.failure(Exception("검색 실패")))
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    onResult(Result.failure(t))
                }
            })
    }
}
