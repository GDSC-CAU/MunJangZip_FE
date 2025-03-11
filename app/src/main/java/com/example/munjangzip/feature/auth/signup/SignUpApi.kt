package com.example.munjangzip.feature.auth.signup

//######## Retrofit API 인터페이스 ########
//#### POST("/member/register")요청  ####
//########## JSON 형식으로 파싱 ###########

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Headers
import java.sql.ResultSetMetaData
import com.google.gson.annotations.SerializedName

// 회원가입 요청 데이터 모델
data class SignUpRequest(
    val nickname: String,
    val libraryName:String,
    val email: String,
    val password: String
)

// 회원가입 응답 데이터 모델
data class SignUpResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
)

// Retrofit API 인터페이스
interface SignUpApi {
    @Headers("Content-Type: application/json")
    @POST("member/register")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

}