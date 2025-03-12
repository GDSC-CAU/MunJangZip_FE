// 엑세스 토큰 재발급 
package com.example.munjangzip.network

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/member/refresh")
    suspend fun refreshAccessToken(@Body request: RefreshTokenRequest): RefreshTokenResponse
}

// 요청 데이터 클래스
data class RefreshTokenRequest(
    val refresh_token: String
)

// 응답 데이터 클래스
data class RefreshTokenResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: TokenResult
)

data class TokenResult(
    val accessToken: String,
    val refreshToken: String
)
