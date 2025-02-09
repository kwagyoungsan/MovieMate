package com.example.moviemate.model

import android.util.Log
import com.example.moviemate.BuildConfig.API_KEY
import com.example.moviemate.model.DailyData.Companion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeeklyData {
    companion object {
        val instance = WeeklyData()
        private const val TAG = "WeeklyData"
    }

    fun getWeeklyData(
        date: Int,
        onSuccess: (List<WeeklyBoxOffice>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val call = RetrofitInstance.getClient()?.create(MyApi::class.java)?.getRangeDt(
            apiKey = API_KEY,
            targetDt = date,
            week = 0
            /**
             * 0 : 주간(월 ~ 일)
             * 1 : 주말 (금 ~ 일)
             * 2 : 주중 (월 ~ 목)
             */
        )

        Log.d(TAG, "API 요청 URL: ${call?.request()?.url}")

        call?.enqueue(object : Callback<WeeklyBoxOfficeResponse?> {
            override fun onResponse(
                call: Call<WeeklyBoxOfficeResponse?>,
                response: Response<WeeklyBoxOfficeResponse?>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Success date: $date")
                    val boxOfficeList = response.body()?.boxOfficeResult?.boxOfficeList ?: emptyList()
                    onSuccess(boxOfficeList)
                    Log.d(TAG, "boxOfficeList size: ${boxOfficeList.size}")
                } else {
                    onFailure(Throwable("Response not successful: ${response.errorBody()}"))
                }
            }

            override fun onFailure(call: Call<WeeklyBoxOfficeResponse?>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}