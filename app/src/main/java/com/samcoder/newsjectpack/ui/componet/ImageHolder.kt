package com.samcoder.newsjectpack.ui.componet

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.samcoder.newsjectpack.R

@Composable
fun ImageHolder(
    imageUrl:String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build(), contentDescription ="Image" ,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        placeholder = painterResource(id = R.drawable.loading_placeholder),
        error = painterResource(id = R.drawable.error))

}


@Composable
fun ImageUrl(
    imageUrl:String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build(), contentDescription ="Image" ,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(100.dp)
            .width(100.dp),
        placeholder = painterResource(id = R.drawable.loading_placeholder),
        error = painterResource(id = R.drawable.error))

}