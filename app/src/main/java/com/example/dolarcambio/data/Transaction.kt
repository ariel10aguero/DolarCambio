package com.example.dolarcambio.data

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "transaction_table")
data class Transaction(
    val id: Int,
    val type: Int,
    val usd: String,
    val ars: String,
    val date: String
): Parcelable
