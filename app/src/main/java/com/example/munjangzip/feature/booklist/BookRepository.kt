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
    suspend fun checkBook(categoryId: Int): BookResponse<BookListResult>? { //카테고리에서 북리스트 확인
        return try {
            val accessToken = runBlocking { userPreferences.accessToken.first() } // 저장된 토큰 가져오기
            if (accessToken.isNullOrEmpty()) {
            Log.e("BookRepository", "Access token is null or empty")

            return null
            }

            //categoryId 값 확인
            Log.d("BookRepository", "Sending request with categoryId: $categoryId")

            val response = api.checkBookList("Bearer $accessToken", categoryId)
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

    suspend fun registerBook(categoryId: Int, title: String, author: String, category: String,
                             coverImageUrl: String, isbn: String): BookResponse<BookResult>? {

            val accessToken = runBlocking { userPreferences.accessToken.first() } // 저장된 토큰 가져오기
            if (accessToken.isNullOrEmpty()) {
                Log.e("BookRepository", "Access token is null or empty")

                return null
            }

            //val response = api.registerBookInfo("Bearer $accessToken", categoryId, BookRequest())


        return api.registerBookInfo("Bearer $accessToken", categoryId,BookRequest(title, author,category, coverImageUrl, isbn))

    }

    //책 상세보기
    suspend fun getBookDetail(bookId: Int): BookResponse<BookDetailResult>? {
        return try {
            val accessToken = runBlocking { userPreferences.accessToken.first() }
            if (accessToken.isNullOrEmpty()) {
                Log.e("BookRepository", "Access token is null or empty")
                return null
            }

            val response = api.getBookDetail("Bearer $accessToken", bookId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            Log.e("BookRepository", "Error fetching book detail: ${e.message}")
            null
        }
    }

}