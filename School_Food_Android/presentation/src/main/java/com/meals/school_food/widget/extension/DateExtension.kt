package com.meals.school_food.widget.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.getTime(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.parse(this)!!
}

fun Date.getFormatDate(): Date {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.parse(format.format(this))!!
}

fun Date.krDateFormat() : String {
    val format = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
    return format.format(this)
}

fun Date.monthDateFormat() : String {
    val format = SimpleDateFormat("yyyyMM", Locale.getDefault())
    return format.format(this)
}

fun Date.dayDateFormat() : String {
    val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    return format.format(this)
}

fun String.getDateFormat() : String {
    val beforeFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
    val afterFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val date = beforeFormat.parse(this)!!
    return afterFormat.format(date)
}

fun String.getDateFormat2() : String {
    val beforeFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val afterFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val date = beforeFormat.parse(this)!!
    return afterFormat.format(date)
}