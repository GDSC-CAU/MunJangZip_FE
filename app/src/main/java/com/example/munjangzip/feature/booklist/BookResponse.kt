package com.example.munjangzip.feature.booklist


data class BookResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: T
)

data class BookResult(
    val memberId: Int,
    val bookId: Int,
    val title: String,
    val author: String,
    val registerAt: String,
    val category: String,
    val coverImageUrl: String,
    val isbn: String
)

data class BookListResult(
    val categoryId: Int,
    val categoryName: String,
    val memberId: Int,
    val books: List<BooksResult>
)

data class BooksResult(
    val bookId: Int,
    val bookImage: String,
    val title: String,
    val author: String
)

data class BookDetailResult(
    val title: String,
    val author: String,
    val coverImageUrl: String,
    val category: String,
    val paragraph: List<Paragraph>,
    val isbn: String
)

data class Paragraph(
    val paragraph_id: Int,
    val content: String?,
    val color: Int,
    val createdAt: String,
    val imageUrl: String?
)
