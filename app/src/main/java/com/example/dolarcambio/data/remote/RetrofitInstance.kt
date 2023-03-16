package com.example.dolarcambio.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    const val HEROKUAPP_API = "https://api-dolar-argentina.herokuapp.com"
    const val DOLARSI_API = "https://www.dolarsi.com/"

    private val retroBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(DOLARSI_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val webService = retroBuilder.create(WebService::class.java)
}