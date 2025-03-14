package com.example.munjangzip.feature.category

import com.example.munjangzip.feature.booklist.GetBookListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetCategoryApi {
    @GET("book/main") // 기존 카테고리 조회 API
    suspend fun getCategory(
        @Header("Authorization") accessToken: String
    ): Response<GetCategoryResponse>

    @GET("book/category/{categoryId}") // 특정 카테고리의 책 목록 조회 API
    suspend fun getBooksByCategory(
        @Header("Authorization") accessToken: String,
        @Path("categoryId") categoryId: Int
    ): Response<GetBookListResponse>
}
