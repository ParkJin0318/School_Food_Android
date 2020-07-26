package com.meals.domain.repository

import com.meals.domain.model.School
import io.reactivex.Single

interface SchoolRepository {
    fun getSchools(name: String): Single<School>
}