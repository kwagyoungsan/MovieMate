package com.example.moviemate.repository

import android.util.Log
import com.example.moviemate.BuildConfig
import com.example.moviemate.mapper.toDomain
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.response.MovieDetailResponse
import com.example.moviemate.view.MovieDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepositoryImpl(
    private val api: MyApi
) : DetailRepository {

    override fun fetchMovieDetail(movieCd: String, onResult: (Result<MovieDetail>) -> Unit) {
        api.getMovieDetail(BuildConfig.API_KEY, movieCd)
            ?.enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                    Log.d("API_DEBUG", "✅ isSuccessful: ${response.isSuccessful}")
                    Log.d("API_DEBUG", "📦 code: ${response.code()}")
                    Log.d("API_DEBUG", "❌ errorBody: ${response.errorBody()?.string()}")
                    Log.d("API_DEBUG", "📤 raw: ${response.raw()}")

                    if (response.isSuccessful) {
                        val detail = response.body()?.movieInfoResult?.movieInfo?.toDomain()
                        if (detail != null) {
                            onResult(Result.success(detail))
                        } else {
                            onResult(Result.failure(Exception("데이터 없음")))
                        }
                    } else {
                        onResult(Result.failure(Exception("API 실패")))
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    onResult(Result.failure(t))
                }
            })
    }
}