package com.example.dolarcambio

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

fun formatCalculatorCurrency(num: Float): String{
    val dfm = DecimalFormatSymbols()
    dfm.decimalSeparator = ','
    dfm.groupingSeparator = '.'
    return DecimalFormat("$###,###.##",dfm).format(num)
}


