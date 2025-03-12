package com.example.munjangzip.feature.savebook

import com.example.munjangzip.data.UserPreferences
import javax.inject.Inject

class GetBookRepository @Inject constructor(
    private val api: BarCodeApi,
    private val userPreferences: UserPreferences
) {
    suspend fun getBookInfo(isbn: String): GetBookResponse? {
        return try {

        val response = api.getBook(isbn)

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