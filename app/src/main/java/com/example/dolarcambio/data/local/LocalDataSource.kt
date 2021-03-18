package com.example.dolarcambio.data.local

import androidx.lifecycle.LiveData
import com.example.dolarcambio.data.Transaction

class LocalDataSource(private val db: TransDatabase) {

    fun getAllTransactions(): LiveData<List<Transaction>> {
        return db.transDao().getAllTransactions()
    }

    suspend fun saveTransaction(transaction: Transaction){
        db.transDao().saveTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction){
        db.transDao().deleteTransaction(transaction)

    }

}