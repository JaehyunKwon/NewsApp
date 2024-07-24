package com.test.mobile.network.response

import com.test.mobile.network.request.Articles

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)