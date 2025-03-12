package com.example.munjangzip.feature.category

data class GetCategoryResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: CategoryResult
)

data class CategoryResult(
    val nickName: String,
    val libraryName: String,
    val categoryList: List<CategoryItem>
)

data class CategoryItem(
    val categoryId: Int,
    val categoryName: String,
    val recentBookCovers: String,
    val bookCount: Int,
    val memoCount: Int
)
