package com.meals.data.repository

import com.meals.data.datasource.SchoolDataSource
import com.meals.domain.model.SchoolInfo
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Completable
import io.reactivex.Single

class SchoolRepositoryImpl(private val schoolDataSource: SchoolDataSource): SchoolRepository {

    override fun getAllSchool(name: String): Single<List<SchoolInfo>> = schoolDataSource.getAllSchool(name)

    override fun getSchool(): Single<SchoolInfo> = schoolDataSource.getSchool()

    override fun insertSchool(schoolInfo: SchoolInfo): Completable = schoolDataSource.insertSchool(schoolInfo)
}