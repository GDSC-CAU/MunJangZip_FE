package com.example.munjangzip.network

import com.example.munjangzip.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { userPreferences.accessToken.first() } ?: ""
        val request = chain.request().newBuilder()

        if (token.isNotEmpty()) {
            request.addHeader("Authorization", "Bearer $token") // 자동으로 토큰 추가
        }

        return chain.proceed(request.build())
    }
}
