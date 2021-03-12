package com.example.dolarcambio

import java.util.*


class CalendarUtils {

    val c = Calendar.getInstance()
    var day = c.get(Calendar.DAY_OF_MONTH)
    var month = c.get(Calendar.MONTH)
    var year = c.get(Calendar.YEAR)

    fun setBtnDate(): String {
        val realMonth = month + 1
        val date = "$day/$realMonth/$year"
        return date
    }

}

