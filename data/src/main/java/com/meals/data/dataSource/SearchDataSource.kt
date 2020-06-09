package com.meals.data.dataSource

import com.meals.data.base.BaseDataSource
import com.meals.data.network.remote.SearchRemote
import com.meals.domain.model.Search
import io.reactivex.Single

class SearchDataSource(override val remote: SearchRemote) : BaseDataSource<SearchRemote>() {

    fun getSchools(name: String): Single<Search> = remote.getSchools(name)
}