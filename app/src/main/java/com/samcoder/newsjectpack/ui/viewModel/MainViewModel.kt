package com.samcoder.newsjectpack.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samcoder.newsjectpack.network.ApiServiceImpl
import com.samcoder.newsjectpack.network.resultHandler
import com.samcoder.newsjectpack.ui.news_screen.NewsScreenEvent
import com.samcoder.newsjectpack.ui.news_screen.NewsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiServiceImpl) :ViewModel() {
    var state by mutableStateOf(NewsScreenState())


    private var searchJob:Job? = null





    fun onEvent(event:NewsScreenEvent){
        when(event){
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getBreakingNews(category = state.category)
            }
            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
                getBreakingNews(category = state.category)
            }
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList()
                )
            }
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)
                    searchForNews(state.searchQuery)
                }

            }
        }
    }

    private fun getBreakingNews(
        category: String
    ){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            apiService.getBreakingNews( category).collectLatest {res ->
                res.resultHandler(loading = {
                    state = state.copy(isLoading = true)
                }, error = {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = res.error
                    )
                }){
                    state = state.copy(
                        articles = res.data?.articles ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    private fun searchForNews(
        query: String
    ){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            apiService.searchForNews(query).collectLatest {res ->
                res.resultHandler(loading = {
                    state = state.copy(isLoading = true)
                }, error = {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = res.error
                    )
                }){
                    state = state.copy(
                        articles = res.data?.articles ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }









}