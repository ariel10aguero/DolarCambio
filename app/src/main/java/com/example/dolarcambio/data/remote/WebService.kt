package com.example.dolarcambio.data.remote

import com.example.dolarcambio.data.DolarApi
import com.example.dolarcambio.data.DolarSi
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

//    @GET("/api/dolaroficial")
//    suspend fun getDolarOficial(): Response<DolarApi>
//
//    @GET("/api/dolarblue")
//    suspend fun getDolarBlue(): Response<DolarApi>

    @GET("/api/api.php?type=valoresprincipales")
    suspend fun getDolarSi(): Response<List<DolarSi>>

}