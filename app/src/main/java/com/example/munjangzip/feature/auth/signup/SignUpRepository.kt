package com.example.munjangzip.feature.auth.signup

// 네트워크 요청 캡슐화 - 힐트 사용
// 회원가입 요청 -> 서버로 보내고 응답 반환
import javax.inject.Inject
import retrofit2.Response

class SignUpRepository @Inject constructor(
    private val api: SignUpApi
) {
    suspend fun registerUser(nickname: String, libraryName: String, email: String, password: String): SignUpResponse {
        val request = SignUpRequest(nickname, libraryName, email, password)
        return api.signUp(request)
    }
}
