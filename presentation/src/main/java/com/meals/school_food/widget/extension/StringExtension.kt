package com.meals.school_food.widget.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.getTime(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.parse(this)!!
}

fun String.getDateFormat() : String {
    val beforeFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
    val afterFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val date = beforeFormat.parse(this)!!
    return afterFormat.format(date)
}