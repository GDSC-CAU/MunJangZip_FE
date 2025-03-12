package com.example.munjangzip.feature.category

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GetCategoryApi {
    @GET("book/main")
    suspend fun getCategory(
        @Header("Authorization") accessToken: String // 헤더에 액세스 토큰 추가
    ): Response<GetCategoryResponse>
}
