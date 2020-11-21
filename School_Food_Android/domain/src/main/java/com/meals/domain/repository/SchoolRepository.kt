package com.meals.domain.repository

import com.meals.domain.model.SchoolInfo
import io.reactivex.Completable
import io.reactivex.Single

interface SchoolRepository {
    fun getAllSchool(name: String): Single<List<SchoolInfo>>

    fun getSchool(): Single<SchoolInfo>

    fun insertSchool(schoolInfo: SchoolInfo): Completable
}