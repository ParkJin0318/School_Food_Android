package com.meals.domain.usecase

import com.meals.domain.base.BaseUseCase
import com.meals.domain.model.SchoolInfo
import com.meals.domain.repository.SchoolRepository
import io.reactivex.Single

class GetSchoolUseCase(
    private val schoolRepository: SchoolRepository
): BaseUseCase<Single<SchoolInfo>>() {

    override fun buildUseCaseObservable(): Single<SchoolInfo> =
        schoolRepository.getSchool()
}