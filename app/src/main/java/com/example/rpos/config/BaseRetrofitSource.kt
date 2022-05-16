package com.example.rpos.config

import com.squareup.moshi.Moshi
import retrofit2.Retrofit

open class BaseRetrofitSource(
    retrofitConfig: RetrofitConfig
) {

    val retrofit: Retrofit = retrofitConfig.retrofit

    private val moshi: Moshi = retrofitConfig.moshi
    private val errorAdapter = moshi.adapter(ErrorResponseBody::class.java)

    class ErrorResponseBody(
        val error: String
    )

}