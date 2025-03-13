package com.example.munjangzip.feature.savebook

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BarCodeApi {
    @GET("barcode/book/{ISBN}")
    suspend fun getBook(@Path("ISBN") isbn: String): Response<GetBookResponse>
}