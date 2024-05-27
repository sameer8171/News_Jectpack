package com.samcoder.newsjectpack.common

import java.text.SimpleDateFormat
import java.util.Locale


   fun formatDate(inputDate: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        val parsedDate = inputFormat.parse(inputDate)
        return parsedDate?.let { outputFormat.format(it) }
    }
