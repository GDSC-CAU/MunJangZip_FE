package com.example.munjangzip.feature.booklist

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BookApi {
    @POST("book/register/{categoryId}")
    suspend fun registerBookInfo(
        @Header("Authorization") accessToken: String,
        @Path("categoryId") categoryId: Int,

        @Body request: BookRequest //요청 바디
    ): BookResponse<BookResult>


    @GET("book/category/{categoryId}")
    suspend fun checkBookList(
        @Header("Authorization") accessToken: String,
        @Path("categoryId") categoryId: Int
    ): Response<BookResponse<BookListResult>>

    //책 상세정보
    @GET("book/detail/{bookId}")
    suspend fun getBookDetail(
        @Header("Authorization") accessToken: String,
        @Path("bookId") bookId: Int
    ): Response<BookResponse<BookDetailResult>>

}