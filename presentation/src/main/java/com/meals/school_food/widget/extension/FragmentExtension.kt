package com.meals.school_food.widget.extension

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.meals.school_food.R

fun Fragment.toast(message: String?) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: Int) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.startActivity(activity: Class<*>) {
    startActivity(Intent(context!!.applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
}

fun Fragment.getColor(res : Int) : Int {
    return resources.getColor(res)
}