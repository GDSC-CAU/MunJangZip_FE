package com.example.munjangzip.feature.addCategory
// view 모델에서 api 요청할 수 있게 레포 만들기

import com.example.munjangzip.data.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
class CategoryRepository @Inject constructor(
    private val api: CategoryApi,
    private val userPreferences: UserPreferences
) {
    suspend fun addCategory(categoryName: String): CategoryResponse {
        val token = runBlocking { userPreferences.accessToken.first() } // 저장된 토큰 가져오기
        return api.addCategory("Bearer $token", CategoryRequest(categoryName)) // 토큰을 포함해서 요청
    }
}