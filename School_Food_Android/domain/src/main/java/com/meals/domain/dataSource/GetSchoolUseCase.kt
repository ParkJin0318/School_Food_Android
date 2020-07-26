package com.meals.domain.dataSource

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.School
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Single

class GetSchoolUseCase(
    private val searchRepository: SchoolRepository
): ParamsUseCase<GetSchoolUseCase.Params, Single<School>>() {

    override fun buildUseCaseObservable(params: Params): Single<School> =
        searchRepository.getSchools(params.name)

    data class Params (
        val name: String
    )
}