package com.example.moviemate.model

import android.util.Log
import com.example.moviemate.BuildConfig.API_KEY
import com.example.moviemate.model.response.WeeklyBoxOffice
import com.example.moviemate.model.response.WeeklyBoxOfficeResponse
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
            weekGb = 0
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
                    val responseBody = response.body()

                    if (responseBody?.boxOfficeResult != null) {  // ✅ Null 체크 추가
                        val boxOfficeList = responseBody.boxOfficeResult.weeklyBoxOfficeList
                            ?: emptyList()
                        Log.d(TAG, "boxOfficeList size: ${boxOfficeList.size}")
                        Log.d(TAG, "원본 응답: ${responseBody.toString()}")  // ✅ 원본 응답을 로그로 출력
                        onSuccess(boxOfficeList)
                    } else {
                        Log.e(TAG, "Response Body or BoxOfficeResult is null")
                        onFailure(Throwable("Response Body or BoxOfficeResult is null"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Response not successful: $errorBody")
                    onFailure(Throwable("Response not successful: $errorBody"))
                }
            }

            override fun onFailure(call: Call<WeeklyBoxOfficeResponse?>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}