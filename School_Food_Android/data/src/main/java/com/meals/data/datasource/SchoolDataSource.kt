package com.meals.data.datasource

import com.meals.data.base.BaseDataSource
import com.meals.data.database.cache.SchoolCache
import com.meals.data.mapper.mapToEntity
import com.meals.data.mapper.mapToModel
import com.meals.data.network.remote.SchoolRemote
import com.meals.domain.model.SchoolInfo
import io.reactivex.Completable
import io.reactivex.Single

class SchoolDataSource(
    override val remote: SchoolRemote,
    override val cache: SchoolCache
) : BaseDataSource<SchoolRemote, SchoolCache>() {

    fun getAllSchool(name: String): Single<List<SchoolInfo>> = remote.getAllSchool(name)

    fun getSchool(): Single<SchoolInfo> = cache.getSchool().map { it.mapToModel() }

    fun insertSchool(schoolInfo: SchoolInfo): Completable =
        cache.deleteAll().andThen(cache.insertSchool(schoolInfo.mapToEntity()))
}