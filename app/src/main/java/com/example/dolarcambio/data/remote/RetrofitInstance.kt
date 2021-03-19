package com.example.dolarcambio.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retroBuilder by lazy {
        Retrofit.Builder()
            .baseUrl("https://api-dolar-argentina.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val webService = retroBuilder.create(WebService::class.java)
}