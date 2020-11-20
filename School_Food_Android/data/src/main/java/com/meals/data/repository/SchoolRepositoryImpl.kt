package com.meals.data.repository

import com.meals.data.datasource.SchoolDataSource
import com.meals.domain.model.School
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Single

class SchoolRepositoryImpl(private val searchDataSource: SchoolDataSource): SchoolRepository {

    override fun getSchools(name: String): Single<School> = searchDataSource.getSchools(name)
}