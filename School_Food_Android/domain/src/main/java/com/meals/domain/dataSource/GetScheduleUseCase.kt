package com.meals.domain.dataSource

import com.meals.domain.base.ParamsUseCase
import com.meals.domain.model.Schedule
import com.meals.domain.repository.ScheduleRepository
import io.reactivex.Single

class GetScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) : ParamsUseCase<GetScheduleUseCase.Params, Single<Schedule>>() {

    override fun buildUseCaseObservable(params: Params): Single<Schedule> =
        scheduleRepository.getSchedules(params.id, params.code, params.date)

    data class Params(
        val id: String,
        val code: String,
        val date: String
    )
}