package com.example.munjangzip.feature.category

import com.example.munjangzip.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetCategoryRepository @Inject constructor(
    private val api: GetCategoryApi,
    private val userPreferences: UserPreferences
) {
    suspend fun getCategories(): GetCategoryResponse? {
        return try {
            // 저장된 `accessToken` 가져오기
            val accessToken = runBlocking { userPreferences.accessToken.first() }
            if (accessToken.isNullOrEmpty()) {
                return null
            }

            // API 요청 (토큰 포함)
            val response = api.getCategory("Bearer $accessToken")

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
