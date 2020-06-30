package com.meals.domain.model

data class School(
    val schools: List<SchoolInformation>
)

data class SchoolInformation(
    val school_name: String,
    val school_locate: String,
    val office_code: String,
    val school_id: String
)