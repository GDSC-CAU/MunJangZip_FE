package com.example.munjangzip.feature.createMemo

import retrofit2.http.Body
import retrofit2.http.POST

interface MemoApiService {
    @POST("/paragraph/create")
    suspend fun createMemo(@Body request: MemoRequest): MemoResponse
}

data class MemoRequest(
    val bookId: Int,
    val paragraph: Paragraph
)

data class Paragraph(
    val content: String,
    val imageURL: String?,
    val color: Int
)

data class MemoResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String
)
