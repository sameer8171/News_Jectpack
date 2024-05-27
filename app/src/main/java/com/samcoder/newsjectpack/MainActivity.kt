package com.samcoder.newsjectpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.samcoder.newsjectpack.ui.theme.NewsJectpackTheme
import com.samcoder.newsjectpack.ui.utils.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsJectpackTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                    val navHostController = rememberNavController()
                    NavGraphSetup(navController = navHostController)

//                    mainViewModel.newsResponse.value?.data?.articles?.let { NewsScreen(article = it) }

                }
            }
        }



    }

    private fun observer(){
//        mainViewModel.newsResponse.observe(this){res ->
//            res.resultHandler(loading = {
//
//            }, error = {
//
//            }, success = {
//                res.data?.articles?.let { value ->
//                    itemList = value
//                }
//            })
//        }
    }


}

