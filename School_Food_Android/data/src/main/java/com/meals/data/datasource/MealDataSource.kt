package com.meals.data.datasource

import com.meals.data.base.BaseDataSource
import com.meals.data.network.remote.MealRemote
import com.meals.domain.model.Meal
import io.reactivex.Single

class MealDataSource(
    override val remote: MealRemote,
    override val cache: Any
) : BaseDataSource<MealRemote, Any>() {

    fun getMeal(id: String, code: String, date: String): Single<Meal> =
        remote.getMeal(id, code, date)
}