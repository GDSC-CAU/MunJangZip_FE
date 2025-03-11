package com.example.munjangzip.feature.addCategory

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CategoryApi {
    @POST("/category")  // 경로
    suspend fun addCategory(
        @Header("Authorization") token: String,
        // Access Token 추가하기 (이렇게하 요청 헤더에 자동으로 입력되게 해놨습니다!)

        @Body request: CategoryRequest // 요청 바디
    ): CategoryResponse
}
