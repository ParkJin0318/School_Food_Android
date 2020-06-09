package com.meals.domain.dataSource

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.Search
import com.meals.domain.repository.SearchRepository
import io.reactivex.Single

class GetSearchUseCase(
    private val searchRepository: SearchRepository
): ParamsUseCase<GetSearchUseCase.Params, Single<Search>>() {

    override fun buildUseCaseObservable(params: Params): Single<Search> =
        searchRepository.getSchools(params.name)

    data class Params (
        val name: String
    )
}