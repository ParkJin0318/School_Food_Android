package com.meals.domain.model

data class Schedule(
    val schedules : List<DetailSchedule>
)

data class DetailSchedule(
    val name: String,
    val date: String
)