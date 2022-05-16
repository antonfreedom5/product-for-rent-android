package com.example.rpos.api

import com.example.rpos.model.Storage
import retrofit2.http.*

interface StorageApi {
    @GET("storage")
    suspend fun getAll(): List<Storage>

    @POST("storage")
    suspend fun add(@Body storage: Storage)

    @DELETE("storage/{id}")
    suspend fun deleteById(@Path("id") id: Long)
}