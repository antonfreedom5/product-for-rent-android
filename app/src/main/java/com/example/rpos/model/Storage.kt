package com.example.rpos.model

data class Storage (
    val id: Long,
    val name: String,
    val phone: String,
    val address: String
        ) {
    override fun toString(): String {
        return "$name, $phone, $address"
    }
}