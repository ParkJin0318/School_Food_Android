package com.meals.data.repository

import com.meals.data.datasource.ScheduleDataSource
import com.meals.data.network.response.ScheduleData
import com.meals.domain.model.ScheduleInfo
import com.meals.domain.repository.ScheduleRepository
import io.reactivex.Single

class ScheduleRepositoryImpl(private val scheduleDataSource: ScheduleDataSource): ScheduleRepository {

    override fun getSchedules(id: String, code: String, date: String): Single<List<ScheduleInfo>> =
        scheduleDataSource.getSchedules(id, code, date)
}