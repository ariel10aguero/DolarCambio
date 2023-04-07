package com.example.dolarcambio.data

data class DolarSi(
    val casa: Casa
)

data class Casa(
    val compra: String,
    val venta: String,
    val agencia: String,
    val nombre: String,
    val variacion: String,
    val ventaCero: String,
    val decimales: String,
    val mejor_compra: String? = null,
    val mejor_venta: String? = null,
    val fecha: String? = null,
    val recorrido: String? = null,
    val afluencia: Any? = null,
    val observaciones: Any? = null
)