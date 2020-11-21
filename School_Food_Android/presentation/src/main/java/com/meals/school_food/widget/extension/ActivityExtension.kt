package com.meals.school_food.widget.extension

import android.content.Intent
import android.content.res.Configuration
import android.view.View
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.setBaseStatusMode() {
    val isNightMode = this.resources.configuration.uiMode
        .and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    var flags = window.decorView.systemUiVisibility

    flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    window.decorView.systemUiVisibility = if (isNightMode) 0 else flags
}

fun AppCompatActivity.startActivity(activity: Class<*>) {
    startActivity(Intent(this, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
}

fun AppCompatActivity.startActivityWithFinish(activity: Class<*>) {
    startActivity(Intent(this, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    this.finish()
}

fun AppCompatActivity.toast(text : Int) {
    android.widget.Toast.makeText(applicationContext, text, android.widget.Toast.LENGTH_SHORT).show()
}