package com.example.dolarcambio

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun closeKeyboard(view: View){
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseDate(apiDate: String?): String{
    val apiFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
    val date = LocalDate.parse(apiDate, apiFormat)
    val outputDate = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
    return outputDate
}


