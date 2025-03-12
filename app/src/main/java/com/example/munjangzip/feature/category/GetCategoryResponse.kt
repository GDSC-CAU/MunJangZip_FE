package com.example.munjangzip.feature.category

data class GetCategoryResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: LibraryData
)

data class LibraryData(
    val nickname: String,
    val libraryName: String,
    val categoryList: List<CategoryItem>
)

data class CategoryItem(
    val categoryId: Int,
    val categoryName: String,
    val recentBookCovers: String?, // 책 표지 URL (없으면 null)
    val bookCount: Int,
    val memoCount: Int
)
