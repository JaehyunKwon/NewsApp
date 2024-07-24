package com.test.mobile.network.request


data class Articles(
    val source: Source,
    val author: String,
    val title: String,
    var description: String?,
    var url: String,
    var urlToImage: String?,
    var publishedAt: String,
    var content: String?
)

data class Source(
    var id : String?,
    var name : String
)