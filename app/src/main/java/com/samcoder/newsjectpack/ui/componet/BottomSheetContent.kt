package com.samcoder.newsjectpack.ui.componet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samcoder.newsjectpack.domain.dto.Article

@Composable
fun BottomSheetContent(
    article: Article,
    onReadFullStoryButtonClick:() ->Unit
) {

    Surface(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleMediumText(text = article.title?:"N/A")
            ViewSpace()
            MediumText(text = article.description ?: "")
            ViewSpace()
            ImageHolder(imageUrl = article.urlToImage)
            ViewSpace()
            SmallText(text = article.content ?: "")
            ViewSpace()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SmallText(text = article.author ?: "")
                SmallText(text = article.source.name ?: "")

            }
            ViewSpace()
            Button(onClick =onReadFullStoryButtonClick,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Read Full Story")
            }


        }
    }




}