package com.meals.data.datasource

import com.meals.data.base.BaseDataSource
import com.meals.data.network.remote.ScheduleRemote
import com.meals.data.network.response.ScheduleData
import com.meals.domain.model.ScheduleInfo
import io.reactivex.Single

class ScheduleDataSource(
    override val remote: ScheduleRemote,
    override val cache: Any,
) : BaseDataSource<ScheduleRemote, Any>() {

    fun getSchedules(id: String, code: String, date: String): Single<List<ScheduleInfo>> =
        remote.getSchedules(id, code, date)
}