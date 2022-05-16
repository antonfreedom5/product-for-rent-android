package com.example.rpos.api

import com.example.rpos.model.Employee
import retrofit2.http.*

interface EmployeeApi {
    @GET("employee")
    suspend fun getAll(): List<Employee>

    @POST("employee")
    suspend fun add(@Body employee: Employee)

    @DELETE("employee/{id}")
    suspend fun deleteById(@Path("id") id: Long)
}