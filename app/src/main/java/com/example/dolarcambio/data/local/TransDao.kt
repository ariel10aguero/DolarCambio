package com.example.dolarcambio.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dolarcambio.data.Transaction


@Dao
interface TransDao {

    @Query("SELECT * FROM transaction_table")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Insert
    suspend fun saveTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

}