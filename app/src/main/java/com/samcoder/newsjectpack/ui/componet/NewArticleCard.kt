package com.samcoder.newsjectpack.ui.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.samcoder.newsjectpack.common.formatDate
import com.samcoder.newsjectpack.domain.dto.Article

@Composable
fun NewArticleCard(
    modifier: Modifier,
    article: Article,
    onCardClicked:(Article) ->Unit
) {
    Card (
        modifier = modifier.clickable { onCardClicked(article) }
    ){
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            ImageHolder(imageUrl = article.urlToImage)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.title ?:"N/A",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
                article.publishedAt?.let {
                    Text(
                        text = formatDate(it) ?: "N/A",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

        }

    }

}

@Composable
fun NewNewsCard(
    modifier: Modifier,
    article: Article,
    onCardClicked: (Article) -> Unit
) {
    Card(
        modifier
            .clickable { onCardClicked(article) },
    ) {
        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Column(modifier = Modifier
                .padding(5.dp)
                .width(200.dp)) {
                Text(text = article.title ?: "N/A",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
                ViewSpace()
                Text(text = article.content?.substringBefore(" [+") ?: "N/A",
                    style = MaterialTheme.typography.bodySmall,
                    )
            }
            ImageUrl(imageUrl = article.urlToImage)
        }
    }
}