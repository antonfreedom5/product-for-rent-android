package com.example.rpos.model

data class Employee (
    val id: Long,
    val name: String,
    val phone: String,
    val address: String,
    val position: Position
        ) {
    override fun toString(): String {
        return "$name, $phone, $address, $position"
    }
}