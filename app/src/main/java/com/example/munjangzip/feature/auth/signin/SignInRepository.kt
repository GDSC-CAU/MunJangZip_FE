package com.example.munjangzip.feature.auth.signin

import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val api: SignInApi
) {
    suspend fun login(email: String, password: String): SignInResponse {
        val request = SignInRequest(email, password)
        return api.signIn(request)
    }
}
