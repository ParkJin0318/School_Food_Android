package com.meals.domain.usecase

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Completable

class InsertSchoolUseCase(
    private val schoolRepository: SchoolRepository
): ParamsUseCase<InsertSchoolUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable =
        schoolRepository.insertSchool(params.schoolInfo)

    data class Params (
        val schoolInfo: SchoolInfo
    )
}