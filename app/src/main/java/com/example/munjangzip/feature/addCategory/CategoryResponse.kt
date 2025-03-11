package com.example.munjangzip.feature.addCategory
//  백엔드 응답 JSON 형식이랑 동일하게 data class 생성하기

data class CategoryResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: CategoryResult
)

data class CategoryResult(
    val categoryId: Int,
    val categoryName: String
)

