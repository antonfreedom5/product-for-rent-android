package com.example.rpos.api

import com.example.rpos.model.Position
import retrofit2.http.*

interface PositionApi {
    @GET("position")
    suspend fun getPositions(): List<Position>

    @POST("position")
    suspend fun add(@Body position: Position)

    @DELETE("position/{id}")
    suspend fun deleteById(@Path("id") id: Long)
}