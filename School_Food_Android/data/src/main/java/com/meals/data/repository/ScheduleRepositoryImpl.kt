package com.meals.data.repository

import com.meals.data.datasource.ScheduleDataSource
import com.meals.data.datasource.SchoolDataSource
import com.meals.data.network.response.ScheduleData
import com.meals.domain.model.ScheduleInfo
import com.meals.domain.repository.ScheduleRepository
import io.reactivex.Single

class ScheduleRepositoryImpl(
    private val scheduleDataSource: ScheduleDataSource,
    private val schoolDataSource: SchoolDataSource
): ScheduleRepository {

    override fun getSchedules(date: String): Single<List<ScheduleInfo>> =
        schoolDataSource.getSchool().flatMap {
            scheduleDataSource.getSchedules(it.school_id, it.office_code, date)
        }
}