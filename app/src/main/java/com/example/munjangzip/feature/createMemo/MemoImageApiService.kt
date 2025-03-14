package com.example.munjangzip.feature.createMemo

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface MemoImageApiService {
    @Multipart
    @POST("/paragraph/{book_id}/image")
    fun uploadMemoImage(
        @Path("book_id") bookId: Int,
        @Query("color") color: Int = 2, // 색상은 항상 2로 고정
        @Part image: MultipartBody.Part
    ): Call<MemoImageResponse>
}

data class MemoImageResponse(
    val isSuccess: Boolean,
    val imageUrl: String? // 백엔드가 반환하는 업로드된 이미지 URL
)
