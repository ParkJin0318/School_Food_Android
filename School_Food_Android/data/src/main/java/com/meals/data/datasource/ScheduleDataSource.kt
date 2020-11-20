package com.meals.data.datasource

import com.meals.data.base.BaseDataSource
import com.meals.data.network.remote.ScheduleRemote
import com.meals.domain.model.Schedule
import io.reactivex.Single

class ScheduleDataSource(override val remote: ScheduleRemote) : BaseDataSource<ScheduleRemote>() {

    fun getSchedules(id: String, code: String, date: String): Single<Schedule> =
        remote.getSchedules(id, code, date)
}