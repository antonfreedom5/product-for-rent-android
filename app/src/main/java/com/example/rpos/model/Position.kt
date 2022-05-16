package com.example.rpos.model

data class Position (
    var id: Long,
    var name: String
        ) {

    override fun toString(): String {
        return name
    }
}