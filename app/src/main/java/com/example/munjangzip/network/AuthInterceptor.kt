package com.example.munjangzip.network

import com.example.munjangzip.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().newBuilder()

        // 저장된 accessToken 가져오기
        val accessToken = runBlocking { userPreferences.accessToken.first() }
        if (!accessToken.isNullOrEmpty()) {
            originalRequest.addHeader("Authorization", "Bearer $accessToken")
        }

        val response = chain.proceed(originalRequest.build())

        // 토큰 만료 (401 Unauthorized) 되면 자동으로 재발급 후 재요청
        if (response.code == 401) {
            synchronized(this) {
                val newToken = runBlocking { refreshAccessToken(userPreferences) }

                if (newToken != null) {
                    // 새로운 accessToken으로 다시 요청
                    val newRequest = originalRequest
                        .header("Authorization", "Bearer $newToken")
                        .build()
                    return chain.proceed(newRequest)
                }
            }
        }

        return response
    }


     //accessToken 만료 시 refreshToken 사용해서 다시 요청

    private fun refreshAccessToken(userPreferences: UserPreferences): String? {
        val refreshToken = runBlocking { userPreferences.refreshToken.first() }
        if (refreshToken.isNullOrEmpty()) {
            return null //refreshToken이 없으면 재발급 불가
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ㅇㅇㅇ/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authApi = retrofit.create(AuthApi::class.java)

        return try {
            val response = runBlocking { authApi.refreshAccessToken(RefreshTokenRequest(refreshToken)) }

            if (response.isSuccess) {
                val newAccessToken = response.result.accessToken
                val newRefreshToken = response.result.refreshToken

                // 새로운 토큰 저장
                runBlocking {
                    userPreferences.saveTokens(newAccessToken, newRefreshToken)
                }

                newAccessToken
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
