package com.meals.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.meals.data.database.dao.SchoolDao
import com.meals.data.database.entity.SchoolEntity

@Database(entities = [SchoolEntity::class], version = 1, exportSchema = false)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

    companion object {

        private var INSTANCE : RoomDatabase? = null

        fun getInstance(context: Context) : RoomDatabase? {
            if (INSTANCE == null) {
                synchronized(RoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RoomDatabase::class.java, "database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}