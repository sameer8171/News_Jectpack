package com.samcoder.newsjectpack.ui.article_screen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.samcoder.newsjectpack.ui.componet.TopNavBar

@Composable
fun ArticleScreen(
    url: String?,
    onBackPress: () -> Unit
) {

    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopNavBar {
                onBackPress.invoke()
            }
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(factory = {
                WebView(context).apply {
                    webViewClient = object :WebViewClient(){
                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading= false
                        }
                    }
                    loadUrl(url ?: "")
                }
            })
            if (isLoading && url != null){
                CircularProgressIndicator()
            }
        }
    }


}