package com.example.dolarcambio.data

data class DolarSi(
    val casa: List<Casa>
    )

data class Casa(
    val compra: String,
    val venta: String,
    val agencia: String,
    val nombre: String,
    val variacion: String,
    val ventaCero: String,
    val decimales: String
)