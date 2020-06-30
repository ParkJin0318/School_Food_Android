package com.meals.data.network.remote

import com.meals.data.base.BaseRemote
import com.meals.data.network.service.SchoolService
import com.meals.domain.model.School
import io.reactivex.Single

class SchoolRemote(override val service: SchoolService): BaseRemote<SchoolService>() {

    fun getSchools(name: String): Single<School> =
        service.getSchools(name).map(getResponseData())
}