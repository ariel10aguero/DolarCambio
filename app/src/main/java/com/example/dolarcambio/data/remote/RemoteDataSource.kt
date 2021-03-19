package com.example.dolarcambio.data.remote

import com.example.dolarcambio.data.DolarApi
import retrofit2.Response

class RemoteDataSource(val webService: WebService) {

    suspend fun getDolarOficial(): Response<DolarApi>{
       return webService.getDolarOficial()
    }

    suspend fun getDolarBlue(): Response<DolarApi>{
        return webService.getDolarOficial()
    }
}