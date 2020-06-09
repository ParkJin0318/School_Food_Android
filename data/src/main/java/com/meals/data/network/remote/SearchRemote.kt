package com.meals.data.network.remote

import com.meals.data.base.BaseRemote
import com.meals.data.network.service.SearchService
import com.meals.domain.model.Search
import io.reactivex.Single

class SearchRemote(override val service: SearchService): BaseRemote<SearchService>() {

    fun getSchools(name: String): Single<Search> =
        service.getSchools(name).map(getResponseData())
}