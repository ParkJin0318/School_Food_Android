package com.meals.data.datasource

import com.meals.data.base.BaseDataSource
import com.meals.data.network.remote.SchoolRemote
import com.meals.domain.model.School
import io.reactivex.Single

class SchoolDataSource(override val remote: SchoolRemote) : BaseDataSource<SchoolRemote>() {

    fun getSchools(name: String): Single<School> = remote.getSchools(name)
}