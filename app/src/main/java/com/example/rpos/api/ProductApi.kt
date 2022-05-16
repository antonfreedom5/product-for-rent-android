package com.example.rpos.api

import com.example.rpos.model.Product
import retrofit2.http.*

interface ProductApi {
    @GET("product")
    suspend fun getAll(): List<Product>

    @POST("product")
    suspend fun add(@Body product: Product)

    @DELETE("product/{id}")
    suspend fun deleteById(@Path("id") id: Long)
}