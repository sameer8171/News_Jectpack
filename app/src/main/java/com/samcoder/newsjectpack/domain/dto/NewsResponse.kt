package com.samcoder.newsjectpack.domain.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)