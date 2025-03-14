package com.example.munjangzip.feature.category

import com.example.munjangzip.data.UserPreferences
import com.example.munjangzip.feature.booklist.GetBookListResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
class GetCategoryRepository @Inject constructor(
    private val api: GetCategoryApi,
    private val userPreferences: UserPreferences
) {
    suspend fun getCategories(): GetCategoryResponse? {
        return try {
            val accessToken = runBlocking { userPreferences.accessToken.first() }
            if (accessToken.isNullOrEmpty()) {
                return null
            }

            val response = api.getCategory("Bearer $accessToken") // API 호출
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getBooksByCategory(categoryId: Int): GetBookListResponse? {
        return try {
            val accessToken = runBlocking { userPreferences.accessToken.first() }
            if (accessToken.isNullOrEmpty()) {
                return null
            }

            val response = api.getBooksByCategory("Bearer $accessToken", categoryId)
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
