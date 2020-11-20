package com.meals.data.network.remote

import com.meals.data.base.BaseRemote
import com.meals.data.network.service.SchoolService
import com.meals.data.network.response.SchoolData
import com.meals.domain.model.SchoolInfo
import io.reactivex.Single

class SchoolRemote(override val service: SchoolService): BaseRemote<SchoolService>() {

    fun getAllSchool(name: String): Single<List<SchoolInfo>> =
        service.getAllSchool(name).map(getResponseData()).map(SchoolData::schools)
}