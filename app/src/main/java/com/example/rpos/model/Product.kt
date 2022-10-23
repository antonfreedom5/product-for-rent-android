package com.example.rpos.model

data class Product (
    val id: Long,
    val name: String,
    val price: Long,
    val storage: Storage,
    val count: Long
        ) {
    override fun toString(): String {
        return "$name, $price, $storage, $count"
    }
}