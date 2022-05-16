package com.example.rpos.model

data class Client (
    val id: Long,
    val name: String,
    val phone: String,
    val address: String,
    val products: List<Product>
        )