package com.meals.domain.usecase

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.ScheduleInfo
import com.meals.domain.repository.ScheduleRepository
import io.reactivex.Single

class GetScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) : ParamsUseCase<GetScheduleUseCase.Params, Single<List<ScheduleInfo>>>() {

    override fun buildUseCaseObservable(params: Params): Single<List<ScheduleInfo>> =
        scheduleRepository.getSchedules(params.id, params.code, params.date)

    data class Params(
        val id: String,
        val code: String,
        val date: String
    )
}