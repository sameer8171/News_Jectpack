package com.samcoder.newsjectpack.ui.componet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onCloseIconClick: () -> Unit,
    onSearchIconClick: () -> Unit
) {

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value, onValueChange = onValueChange,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "Search icon",
                tint = Color.White.copy(alpha = 0.7f)
            )
        },
        placeholder = { Text(text = "Search...", color = Color.White.copy(alpha = 0.7f)) },
        trailingIcon = {
            IconButton(onClick = { if (value.isNotEmpty()) onValueChange("") else onCloseIconClick() }) {
                Icon(
                    imageVector = Icons.Filled.Close, contentDescription = "close",
                    tint = Color.White
                )
            }
        },
        keyboardActions = KeyboardActions(onSearch = {onSearchIconClick()}),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White
        )
    )

}