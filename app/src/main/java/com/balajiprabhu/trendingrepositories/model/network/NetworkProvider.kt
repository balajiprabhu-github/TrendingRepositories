package com.balajiprabhu.trendingrepositories.model.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.HttpUrl
import okhttp3.Request


object NetworkProvider {

    private const val BASE_URL = "https://api.github.com/"
    private const val QUERY_SORT = "sort"
    private const val QUERY_SORT_BY_STAR = "star"

    private fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(QUERY_SORT, QUERY_SORT_BY_STAR)
                .build()
            val requestBuilder: Request.Builder = original.newBuilder().url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

    }

    private fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(providesInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()

    fun providesAppClient() : GithubSearchRepositoryService =
        providesRetrofit().create(GithubSearchRepositoryService::class.java)

}