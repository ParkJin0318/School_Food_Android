package com.meals.data.repository

import com.meals.data.dataSource.SearchDataSource
import com.meals.domain.model.Search
import com.meals.domain.repository.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl(private val searchDataSource: SearchDataSource): SearchRepository {

    override fun getSchools(name: String): Single<Search> = searchDataSource.getSchools(name)
}