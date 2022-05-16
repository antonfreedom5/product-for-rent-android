package com.example.rpos.api

import com.example.rpos.model.Client
import retrofit2.http.*

interface ClientApi {
    @GET("client")
    suspend fun getAll(): List<Client>

    @POST("client")
    suspend fun add(@Body client: Client)

    @DELETE("client/{id}")
    suspend fun deleteById(@Path("id") id: Long)
}