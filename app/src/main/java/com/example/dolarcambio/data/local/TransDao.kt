package com.example.dolarcambio.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dolarcambio.data.Transaction


@Dao
interface TransDao {

    @Query("SELECT * FROM transaction_table")
    fun getAllTransactions(): LiveData<MutableList<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

}