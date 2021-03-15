package com.example.dolarcambio.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val id: Int,
    val type: Int,
    val usd: String,
    val ars: String,
    val date: String
): Parcelable
