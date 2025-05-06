package com.example.moviemate.repository

import android.util.Log
import com.example.moviemate.BuildConfig
import com.example.moviemate.model.MyApi
import com.example.moviemate.model.RetrofitInstance
import com.example.moviemate.model.response.DailyBoxOffice
import com.example.moviemate.model.response.DailyResponse
import com.example.moviemate.model.response.WeeklyBoxOffice
import com.example.moviemate.model.response.WeeklyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRankRepositoryImpl(
    private val api: MyApi = RetrofitInstance.getBoxOffice()!!.create(MyApi::class.java)
) : MovieRankRepository {

    override fun getDailyBoxOffice(date: Int, onResult: (Result<List<DailyBoxOffice>>) -> Unit) {
        api.getDailyBoxOffice(BuildConfig.API_KEY, date).enqueue(object : Callback<DailyResponse> {
            override fun onResponse(call: Call<DailyResponse>, response: Response<DailyResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.boxOfficeResult?.dailyBoxOfficeList.orEmpty()
                    onResult(Result.success(data))
                } else {
                    val error = "일간 API 실패: code=${response.code()}, message=${response.message()}"
                    Log.e("MovieRankRepository", error)
                    onResult(Result.failure(Exception(error)))
                }
            }

            override fun onFailure(call: Call<DailyResponse>, t: Throwable) {
                Log.e("MovieRankRepository", "일간 API 호출 실패", t)
                onResult(Result.failure(t))
            }
        })
    }

    override fun getWeeklyBoxOffice(date: Int, onResult: (Result<List<WeeklyBoxOffice>>) -> Unit) {
        api.getWeeklyBoxOffice(BuildConfig.API_KEY, date).enqueue(object : Callback<WeeklyResponse> {
            override fun onResponse(call: Call<WeeklyResponse>, response: Response<WeeklyResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.boxOfficeResult?.weeklyBoxOfficeList.orEmpty()
                    onResult(Result.success(data))
                } else {
                    val error = "주간 API 실패: code=${response.code()}, message=${response.message()}"
                    Log.e("MovieRankRepository", error)
                    onResult(Result.failure(Exception(error)))
                }
            }

            override fun onFailure(call: Call<WeeklyResponse>, t: Throwable) {
                Log.e("MovieRankRepository", "주간 API 호출 실패", t)
                onResult(Result.failure(t))
            }
        })
    }
}
