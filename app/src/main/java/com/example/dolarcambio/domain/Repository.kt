package com.example.dolarcambio.domain

import androidx.lifecycle.LiveData
import com.example.dolarcambio.data.Transaction

interface Repository {

    fun getAllTransactions(): LiveData<MutableList<Transaction>>
    suspend fun saveTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)

}