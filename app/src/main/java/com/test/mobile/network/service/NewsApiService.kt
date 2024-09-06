package com.test.mobile.network.service

import com.test.mobile.network.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "208b6b4a70b34a3a966b02520ec49f0e"
    ): Response<NewsResponse>
}