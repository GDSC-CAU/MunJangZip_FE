package com.example.munjangzip.feature.booklist

data class BookRequest(
    val title: String,
    val author: String,
    val category: String,
    val coverImageUrl: String,
    val isbn: String
)

