package com.example.dolarcambio.data.remote

import com.example.dolarcambio.data.DolarApi
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    @GET("/api/dolaroficial")
    suspend fun getDolarOficial(): Response<DolarApi>

    @GET("/api/dolarblue")
    suspend fun getDolarBlue(): Response<DolarApi>

}