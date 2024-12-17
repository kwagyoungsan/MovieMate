package com.example.moviemate.model

import android.util.Log
import com.example.moviemate.BuildConfig.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        call?.enqueue(object : Callback<BoxOfficeResponse?> {
            override fun onResponse(
                call: Call<BoxOfficeResponse?>,
                response: Response<BoxOfficeResponse?>
            ) {
                if (response.isSuccessful) {
                    val boxOfficeList = response.body()?.boxOfficeResult?.dailyBoxOfficeList ?: emptyList()
                    onSuccess(boxOfficeList)
                } else {
                    onFailure(Throwable("Response not successful: ${response.errorBody()}"))
                }
            }

            override fun onFailure(call: Call<BoxOfficeResponse?>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}