package com.example.moviemate.model

import android.util.Log
import com.example.moviemate.BuildConfig.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyData {
    companion object {
        val instance = DailyData()
        private const val TAG = "DailyData"
    }

    fun getDailyData(
        date: Int,
        onSuccess: (List<DailyBoxOffice>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val call = RetrofitInstance.getClient()?.create(MyApi::class.java)?.getTargetDt(
            apiKey = API_KEY,
            targetDt = date
        )

        Log.d(TAG, "API 요청 URL: ${call?.request()?.url}")

        call?.enqueue(object : Callback<DailyBoxOfficeResponse?> {
            override fun onResponse(
                call: Call<DailyBoxOfficeResponse?>,
                response: Response<DailyBoxOfficeResponse?>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Success date: $date")
                    val boxOfficeList = response.body()?.boxOfficeResult?.dailyBoxOfficeList ?: emptyList()
                    onSuccess(boxOfficeList)
                } else {
                    onFailure(Throwable("Response not successful: ${response.errorBody()}"))
                }
            }

            override fun onFailure(call: Call<DailyBoxOfficeResponse?>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}