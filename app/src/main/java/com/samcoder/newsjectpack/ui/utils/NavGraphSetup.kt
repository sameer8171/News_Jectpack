package com.samcoder.newsjectpack.ui.utils

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.samcoder.newsjectpack.ui.article_screen.ArticleScreen
import com.samcoder.newsjectpack.ui.news_screen.NewsScreen
import com.samcoder.newsjectpack.ui.viewModel.MainViewModel


@Composable
fun NavGraphSetup(navController: NavHostController) {
    val argKey = "web_url"
    NavHost(navController = navController, startDestination = "news_screen") {

        composable(route = "news_screen") {
            val viewModel: MainViewModel = hiltViewModel()
            NewsScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent
            ) { url ->
                navController.navigate("article_screen?$argKey=$url")
            }
        }

        composable(route = "article_screen?$argKey={$argKey}",
            arguments = listOf(
                navArgument(name = argKey) {
                    type = NavType.StringType
                }
            )
        ) {navBackStackEntry ->
            ArticleScreen(url = navBackStackEntry.arguments?.getString(argKey)) {
                navController.navigateUp()
            }
        }
    }

}