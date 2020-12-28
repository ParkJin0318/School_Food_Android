package com.meals.school_food.widget.extension

import java.text.SimpleDateFormat
import java.util.*

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