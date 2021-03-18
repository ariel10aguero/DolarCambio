package com.example.dolarcambio.data.local

import androidx.lifecycle.LiveData
import com.example.dolarcambio.data.Transaction

class LocalDataSource(private val transDao: TransDao) {

    fun getAllTransactions(): LiveData<List<Transaction>> {
        return transDao.getAllTransactions()
    }

    suspend fun saveTransaction(transaction: Transaction){
        transDao.saveTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction){
        transDao.deleteTransaction(transaction)

    }

}