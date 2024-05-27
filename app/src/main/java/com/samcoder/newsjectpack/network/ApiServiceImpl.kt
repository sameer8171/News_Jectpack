package com.samcoder.newsjectpack.network

import com.samcoder.newsjectpack.domain.dto.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {

    suspend fun getBreakingNews(
        category: String
    ): Flow<NetworkResult<NewsResponse>> {
        return flow { emit(safeApiCall { apiService.getBreakingNews(category) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchForNews(
        query: String
    ): Flow<NetworkResult<NewsResponse>> {
        return flow { emit(safeApiCall { apiService.searchForNews(query) }) }.flowOn(Dispatchers.IO)
    }
}