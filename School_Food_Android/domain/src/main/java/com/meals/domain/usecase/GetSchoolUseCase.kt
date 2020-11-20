package com.meals.domain.usecase

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Single

class GetSchoolUseCase(
    private val searchRepository: SchoolRepository
): ParamsUseCase<GetSchoolUseCase.Params, Single<List<SchoolInfo>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<SchoolInfo>> =
        searchRepository.getSchools(params.name)

    data class Params (
        val name: String
    )
}