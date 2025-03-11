package com.example.munjangzip.feature.auth.signin

import retrofit2.http.Body
import retrofit2.http.POST

// 로그인 요청 데이터 모델
data class SignInRequest(
    val email: String,
    val password: String
)

// 로그인 응답 데이터 모델
data class SignInResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: TokenData? // 성공하면 토큰 발급
)

// 반환 데이터
data class TokenData(
    val accessToken: String,
    val refreshToken: String,
    val memberId: Int
)

// Retrofit API 인터페이스
interface SignInApi {
    @POST("member/login/email")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse
}
