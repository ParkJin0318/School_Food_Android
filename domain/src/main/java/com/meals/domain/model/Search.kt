package com.meals.domain.model

data class Search(
    val schools: List<DetailSearch>
)

data class DetailSearch(
    val school_name: String,
    val school_locate: String,
    val office_code: String,
    val school_id: String
)