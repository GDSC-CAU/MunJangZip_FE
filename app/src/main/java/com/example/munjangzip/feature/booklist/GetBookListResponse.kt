package com.example.munjangzip.feature.booklist

data class GetBookListResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: BookListResult
)

data class BookListResult(
    val categoryId: Int,
    val categoryName: String,
    val books: List<BookItem>
)

data class BookItem(
    val bookId: Int,
    val bookImage: String,
    val title: String,
    val author: String
)
