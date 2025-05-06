package com.example.moviemate.di

import com.example.moviemate.model.MyApi
import com.example.moviemate.repository.DetailRepository
import com.example.moviemate.repository.DetailRepositoryImpl
import com.example.moviemate.repository.MovieRankRepository
import com.example.moviemate.repository.MovieRankRepositoryImpl
import com.example.moviemate.repository.SearchRepository
import com.example.moviemate.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): MyApi =
        retrofit.create(MyApi::class.java)

    @Provides
    @Singleton
    fun provideSearchRepository(api: MyApi): SearchRepository =
        SearchRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideMovieRankRepository(api: MyApi): MovieRankRepository =
        MovieRankRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDetailRepository(api: MyApi): DetailRepository =
        DetailRepositoryImpl(api)
}