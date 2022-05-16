package com.example.rpos.source

import com.example.rpos.api.PositionApi
import com.example.rpos.model.Position

class PositionsSource : BaseSource() {
    private val positionApi = retrofit.create(PositionApi::class.java)

    suspend fun getPositions(): List<Position> = positionApi.getPositions()

    suspend fun deleteById(id: Long): Unit = positionApi.deleteById(id)

    suspend fun add(position: Position) = positionApi.add(position)
}