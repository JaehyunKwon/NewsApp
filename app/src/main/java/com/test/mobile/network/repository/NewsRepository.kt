package com.test.mobile.network.repository

import com.test.mobile.network.response.NewsResponse
import com.test.mobile.network.service.NewsApiService
import retrofit2.Response
import javax.inject.Inject

class NewsRepository
@Inject
constructor(
    private val service: NewsApiService
) {
    suspend fun getNews() : Response<NewsResponse> {
        return service.getNews()
    }
}