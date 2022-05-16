package com.example.rpos.source

import com.example.rpos.api.ProductApi
import com.example.rpos.model.Product

class ProductSource : BaseSource() {
    private val productApi = retrofit.create(ProductApi::class.java)

    suspend fun getProducts(): List<Product> = productApi.getAll()

    suspend fun add(product: Product) = productApi.add(product)

    suspend fun deleteById(id: Long): Unit = productApi.deleteById(id)
}