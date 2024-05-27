package com.samcoder.newsjectpack.network

import com.samcoder.newsjectpack.common.Constants.API_KEY
import com.samcoder.newsjectpack.domain.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/** Created by SamCoder 14 Feb 2024*/
interface ApiService {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = API_KEY
    ):Response<NewsResponse>


    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

}