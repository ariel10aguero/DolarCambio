package com.example.dolarcambio.domain

import androidx.lifecycle.LiveData
import com.example.dolarcambio.data.DolarApi
import com.example.dolarcambio.data.Transaction
import com.example.dolarcambio.data.local.LocalDataSource
import com.example.dolarcambio.data.remote.RemoteDataSource
import retrofit2.Response

class RepoImplement(private val dataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource): Repository {

    override fun getAllTransactions(): LiveData<MutableList<Transaction>> {
       return dataSource.getAllTransactions()
    }

    override suspend fun saveTransaction(transaction: Transaction) {
        dataSource.saveTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        dataSource.deleteTransaction(transaction)
    }

    override suspend fun getDolarOficial(): Response<DolarApi> {
        return remoteDataSource.getDolarOficial()
    }

    override suspend fun getDolarBlue(): Response<DolarApi> {
        return remoteDataSource.getDolarBlue()
    }
}