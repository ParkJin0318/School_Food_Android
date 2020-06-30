package com.meals.data.network.service

import com.meals.data.network.data.Response
import com.meals.domain.model.School
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {
    @GET("search")
    fun getSchools(@Query("school_name", encoded = true) name: String) :
            Single<retrofit2.Response<Response<School>>>
}