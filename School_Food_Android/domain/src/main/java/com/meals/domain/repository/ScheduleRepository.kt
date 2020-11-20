package com.meals.domain.repository

import com.meals.domain.model.ScheduleInfo
import io.reactivex.Single

interface ScheduleRepository {
    fun getSchedules(date: String): Single<List<ScheduleInfo>>
}