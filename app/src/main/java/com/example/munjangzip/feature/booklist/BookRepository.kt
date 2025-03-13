package com.example.munjangzip.feature.booklist

import android.util.Log
import com.example.munjangzip.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BookApi,
    private val userPreferences: UserPreferences
) {
    suspend fun checkBook(categoryId: Int): BookResponse<BookListResult>? {
        return try {
            val accessToken = runBlocking { userPreferences.accessToken.first() } // 저장된 토큰 가져오기
            if (accessToken.isNullOrEmpty()) {
            Log.e("BookRepository", "Access token is null or empty")

            return null
            }

            // ✅ categoryId 값 확인
            Log.d("BookRepository", "Sending request with categoryId: $categoryId")

            val response = api.checkBookList("Bearer $accessToken", categoryId)
            Log.d("BookRepository", "API Response: ${response.body()}") // ✅ API 응답 확인
            Log.d("BookRepository", "API Response: ${response.body()}") // ✅ 응답 데이터 확인

            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("BookRepository", "API call failed with code: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("BookRepository", "Error fetching books: ${e.message}")

            null
        }
    }

}