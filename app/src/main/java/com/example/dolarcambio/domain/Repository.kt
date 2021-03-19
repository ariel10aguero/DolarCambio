package com.example.dolarcambio.domain

import androidx.lifecycle.LiveData
import com.example.dolarcambio.data.DolarApi
import com.example.dolarcambio.data.Transaction
import retrofit2.Response

interface Repository {

    fun getAllTransactions(): LiveData<MutableList<Transaction>>
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun getDolarOficial(): Response<DolarApi>
    suspend fun getDolarBlue(): Response<DolarApi>

}