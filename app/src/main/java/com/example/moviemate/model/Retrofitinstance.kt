package com.example.moviemate.model

import com.example.moviemate.model.API.BASE_URL_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance{
    private var retrofitClient: Retrofit? = null

    fun getClient(): Retrofit? {

        val client = OkHttpClient.Builder()

        // 로그를 찍기 위해 로깅 인터셉터 설정
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // 위에서 설정한 로깅 인터셉터를 okhttp 클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)

        client.connectTimeout(1000, TimeUnit.SECONDS)
        client.readTimeout(1000, TimeUnit.SECONDS)
        client.writeTimeout(1000, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)

        if (retrofitClient == null) {

            // 레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                // 위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                .client(client.build())
                .build()

        }

        return retrofitClient
    }
}