package com.meals.domain.repository

import com.meals.domain.model.ScheduleInfo
import io.reactivex.Single

interface ScheduleRepository {
    fun getSchedules(id: String, code: String, date: String): Single<List<ScheduleInfo>>
}