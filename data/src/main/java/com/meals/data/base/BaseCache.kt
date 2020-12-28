package com.meals.data.base

import android.app.Application
import com.meals.data.database.RoomDatabase

open class BaseCache(application: Application) {
    protected val database = RoomDatabase.getInstance(application)!!
}