package com.example.munjangzip.feature.booklist

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BookApi {
    @POST("book/register/{categoryId}")
    suspend fun registerBook(
        @Header("Authorization") token: String,

        @Body request: BookRequest //요청 바디
    ): BookResponse<BooksResult>


    @GET("book/category/{categoryId}")
    suspend fun checkBookList(
        @Header("Authorization") accessToken: String,
    ): Response<BookResponse<BookListResult>>
}