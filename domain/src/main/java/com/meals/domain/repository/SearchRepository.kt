package com.meals.domain.repository

import com.meals.domain.model.Search
import io.reactivex.Single

interface SearchRepository {
    fun getSchools(name: String): Single<Search>
}