package com.example.moviemate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviemate.BuildConfig
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.RetrofitInstance
import com.example.moviemate.model.response.Movie
import com.example.moviemate.model.response.SearchMovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults


    companion object {
        private const val TAG = "SearchViewModel"
    }

    fun searchMovieByMovieNm(query: String) {
        val apiService = RetrofitInstance.getMovieInfo()?.create(MyApi::class.java)
        apiService?.searchMovieListByMovieNm(BuildConfig.API_KEY, query)
            ?.enqueue(object : Callback<SearchMovieResponse> {
                override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                    if (response.isSuccessful) {
                        _searchResults.value = response.body()?.movieListResult?.movieList ?: emptyList()
                        Log.d(TAG, "영화명 검색 성공")
                    } else {
                        Log.e(TAG, "영화명 검색 실패: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.e(TAG, "영화명 검색 실패", t)
                }
            })
    }

    fun searchMovieByDirectorNm(query: String) {
        val apiService = RetrofitInstance.getMovieInfo()?.create(MyApi::class.java)
        apiService?.searchMovieListByDirectorNm(BuildConfig.API_KEY, query)
            ?.enqueue(object : Callback<SearchMovieResponse> {
                override fun onResponse(call: Call<SearchMovieResponse>, response: Response<SearchMovieResponse>) {
                    if (response.isSuccessful) {
                        _searchResults.value = response.body()?.movieListResult?.movieList ?: emptyList()
                        Log.d(TAG, "감독명 검색 성공")
                    } else {
                        Log.e(TAG, "감독명 검색 실패: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.e(TAG, "감독명 검색 실패", t)
                }
            })
    }
}
