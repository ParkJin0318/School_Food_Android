package com.meals.data.database.cache

import android.app.Application
import com.meals.data.base.BaseCache
import com.meals.data.database.dao.SchoolDao
import com.meals.data.database.entity.SchoolEntity
import io.reactivex.Completable
import io.reactivex.Single

class SchoolCache(application: Application) : BaseCache(application) {

    private val schoolDao : SchoolDao = database.schoolDao()

    fun insertSchool(schoolEntity: SchoolEntity): Completable = schoolDao.insert(schoolEntity)

    fun getSchool(): Single<SchoolEntity> = schoolDao.getSchool()

    fun deleteAll(): Completable = schoolDao.deleteAll()
}