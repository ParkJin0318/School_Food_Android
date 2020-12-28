package com.meals.domain.usecase

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Single

class GetAllSchoolUseCase(
    private val schoolRepository: SchoolRepository
): ParamsUseCase<GetAllSchoolUseCase.Params, Single<List<SchoolInfo>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<SchoolInfo>> =
        schoolRepository.getAllSchool(params.name)

    data class Params (
        val name: String
    )
}