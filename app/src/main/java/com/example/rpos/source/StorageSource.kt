package com.example.rpos.source

import com.example.rpos.api.StorageApi
import com.example.rpos.model.Storage

class StorageSource : BaseSource() {
    private val storageApi = retrofit.create(StorageApi::class.java)

    suspend fun getStorages(): List<Storage> = storageApi.getAll()

    suspend fun add(storage: Storage) = storageApi.add(storage)

    suspend fun deleteById(id: Long): Unit = storageApi.deleteById(id)
}