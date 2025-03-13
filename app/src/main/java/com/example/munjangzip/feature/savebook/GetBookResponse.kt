package com.example.munjangzip.feature.savebook

data class GetBookResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: BookResult
)

data class BookResult(
    val memberId: Int,
    val bookId: Int,
    val title: String,
    val author: String,
    val registerAt: String,  // `register_at` -> `registerAt` (카멜 케이스 적용)
    val category: String,
    val coverImageUrl: String,
    val isbn: String
)