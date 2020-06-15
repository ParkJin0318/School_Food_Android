package com.meals.school_food.widget.extension

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String?) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: Int) {
    Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.startActivity(activity: Class<*>) {
    startActivity(Intent(context!!.applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}