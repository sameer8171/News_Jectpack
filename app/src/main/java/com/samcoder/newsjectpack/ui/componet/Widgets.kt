package com.samcoder.newsjectpack.ui.componet

import android.graphics.fonts.Font
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NormalText(
    text:String,
    color: Color = Color.Black,
    font: TextUnit = 18.sp
) {
    Text(text = text,color = color, fontSize = font)
}

@Composable
fun TitleMediumText(
    text:String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun MediumText(
    text:String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun SmallText(
    text:String,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text =text,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = fontWeight
    )
}

@Composable
fun ViewSpace(
    height: Dp =8.dp
) {
    Spacer(modifier = Modifier.height(height))
}
