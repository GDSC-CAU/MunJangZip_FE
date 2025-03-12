package com.example.munjangzip.feature.auth.signin

import com.example.munjangzip.data.UserPreferences

class SignInRepository(
    private val api: SignInApi,
    private val userPreferences: UserPreferences
) {
    suspend fun signIn(email: String, password: String): SignInResponse {
        return api.signIn(SignInRequest(email, password))
    }

    fun saveTokens(accessToken: String, refreshToken: String) {
        userPreferences.saveTokens(accessToken, refreshToken)
    }
}
