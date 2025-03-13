package com.example.munjangzip.feature.booklist

import com.example.munjangzip.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BookApi,
    private val userPreferences: UserPreferences
) {
    suspend fun checkBook(): BookResponse<BookListResult>? {
        return try {
        val accessToken = runBlocking { userPreferences.accessToken.first() } // 저장된 토큰 가져오기
        if (accessToken.isNullOrEmpty()) {
            return null
        }

        val response = api.checkBookList("Bearer $accessToken")

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