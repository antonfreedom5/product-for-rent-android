package com.example.rpos.source

import com.example.rpos.api.ClientApi
import com.example.rpos.model.Client

class ClientSource: BaseSource() {
    private val clientApi = retrofit.create(ClientApi::class.java)

    suspend fun getClients(): List<Client> = clientApi.getAll()

    suspend fun add(client: Client) = clientApi.add(client)

    suspend fun deleteById(id: Long): Unit = clientApi.deleteById(id)
}