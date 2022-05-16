package com.example.rpos.source

import com.example.rpos.util.Constants
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseSource {
    val moshi = Moshi.Builder().build()
    val moshiConverterFactory = MoshiConverterFactory.create(moshi)
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .build()
}