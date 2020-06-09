package com.meals.data.network.remote

import com.meals.data.base.BaseRemote
import com.meals.data.network.service.ScheduleService
import com.meals.domain.model.Schedule
import io.reactivex.Single

class ScheduleRemote(override val service: ScheduleService): BaseRemote<ScheduleService>() {

    fun getSchedules(id: String, code: String, date: String): Single<Schedule> =
        service.getSchedules(id, code, date).map(getResponseData())
}