package com.example.dolarcambio.data

data class Transaction(
    val id: Int,
    val type: Int,
    val usd: String,
    val ars: String,
    val date: String
)
