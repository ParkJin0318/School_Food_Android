package com.meals.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.meals.data.base.BaseDao
import com.meals.data.database.entity.SchoolEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SchoolDao : BaseDao<SchoolEntity> {

    @Query("SELECT * FROM school_table WHERE idx = 0")
    fun getSchool(): Single<SchoolEntity>

    @Query("DELETE FROM school_table")
    fun deleteAll(): Completable
}