package com.example.dolarcambio.domain

import androidx.lifecycle.LiveData
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.data.local.LocalDataSource

class RepoImplement(private val dataSource: LocalDataSource): Repository {

    override fun getAllTransactions(): LiveData<MutableList<Transaction>> {
       return dataSource.getAllTransactions()
    }

    override suspend fun saveTransaction(transaction: Transaction) {
        dataSource.saveTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        dataSource.deleteTransaction(transaction)
    }
}