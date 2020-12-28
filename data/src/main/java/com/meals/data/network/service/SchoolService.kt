package com.meals.data.network.service

import com.meals.data.network.response.Response
import com.meals.data.network.response.SchoolData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {
    @GET("search")
    fun getAllSchool(@Query("school_name", encoded = true) name: String) :
            Single<retrofit2.Response<Response<SchoolData>>>
}