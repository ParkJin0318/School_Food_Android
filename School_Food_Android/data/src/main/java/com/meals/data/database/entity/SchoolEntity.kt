package com.meals.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school_table")
data class SchoolEntity(
    @PrimaryKey
    val idx: Int,
    val name: String,
    val locate: String,
    val code: String,
    val id: String
)