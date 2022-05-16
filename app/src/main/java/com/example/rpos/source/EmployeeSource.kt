package com.example.rpos.source

import com.example.rpos.api.EmployeeApi
import com.example.rpos.model.Employee

class EmployeeSource: BaseSource() {
    private val employeeApi = retrofit.create(EmployeeApi::class.java)

    suspend fun getEmployees(): List<Employee> = employeeApi.getAll()

    suspend fun deleteById(id: Long): Unit = employeeApi.deleteById(id)

    suspend fun add(employee: Employee) = employeeApi.add(employee)
}