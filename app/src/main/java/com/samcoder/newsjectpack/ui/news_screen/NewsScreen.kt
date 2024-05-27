package com.samcoder.newsjectpack.ui.news_screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.samcoder.newsjectpack.domain.dto.Article
import com.samcoder.newsjectpack.ui.componet.BottomSheetContent
import com.samcoder.newsjectpack.ui.componet.CategoryTabRow
import com.samcoder.newsjectpack.ui.componet.NewNewsCard
import com.samcoder.newsjectpack.ui.componet.NewScreenTopBar
import com.samcoder.newsjectpack.ui.componet.RetryContent
import com.samcoder.newsjectpack.ui.componet.SearchAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onEvent: (NewsScreenEvent) -> Unit,
    onReadFullStoryButtonClick: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )

    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState {
        categories.size
    }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onEvent(NewsScreenEvent.OnCategoryChanged(category = categories[page]))
        }
    }
    
    LaunchedEffect(key1 = Unit){
        if (state.searchQuery.isNotEmpty()){
            onEvent(NewsScreenEvent.OnSearchQueryChanged(searchQuery = state.searchQuery))
        }

    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember {
        mutableStateOf(false)
    }

    if (shouldBottomSheetShow) {
        ModalBottomSheet(onDismissRequest = { shouldBottomSheetShow = false },
            sheetState = sheetState, content = {
                state.selectedArticle?.let { value ->
                    BottomSheetContent(article = value) {
                        onReadFullStoryButtonClick(value.url)
                        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) shouldBottomSheetShow = false
                        }
                    }
                }
            })
    }


    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Crossfade(targetState = state.isSearchBarVisible, label = "") { isVisible ->
            if (isVisible) {
                Column {

                    SearchAppBar(
                        modifier = Modifier.focusRequester(focusRequester),
                        value = state.searchQuery, onValueChange = { newValue ->
                            onEvent(NewsScreenEvent.OnSearchQueryChanged(newValue))
                        }, onCloseIconClick = { onEvent(NewsScreenEvent.OnCloseIconClicked) }, onSearchIconClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        })
                    NewsArticleList(state = state, onCardClicked =
                    { article ->
                        shouldBottomSheetShow = true
                        onEvent(NewsScreenEvent.OnNewsCardClicked(article = article))
                    }) {
                        onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                    }
                }
            } else {
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        NewScreenTopBar(scrollBehavior = scrollBehavior) {
                            onEvent(NewsScreenEvent.OnSearchIconClicked)
                            coroutineScope.launch {
                                delay(500)
                                focusRequester.requestFocus()
                            }
                        }
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                    ) {
                        CategoryTabRow(
                            pagerState = pagerState,
                            categories = categories,
                            onTabSelected = { index ->
                                coroutineScope.launch { pagerState.animateScrollToPage(index) }
                            })
                        HorizontalPager(state = pagerState) {
                            NewsArticleList(state = state, onCardClicked =
                            { article ->
                                shouldBottomSheetShow = true
                                onEvent(NewsScreenEvent.OnNewsCardClicked(article = article))
                            }) {
                                onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                            }
                        }
                    }

                }
            }

        }
    }


}

@Composable
fun NewsArticleList(
    state: NewsScreenState,
    onCardClicked: (Article) -> Unit,
    onRetry: () -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.articles) { article ->
            NewNewsCard(modifier = Modifier, article = article, onCardClicked = {
                onCardClicked(article)
            })

        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,

    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.error != null) {
            RetryContent(error = state.error) {
                onRetry()
            }
        }
    }

}

