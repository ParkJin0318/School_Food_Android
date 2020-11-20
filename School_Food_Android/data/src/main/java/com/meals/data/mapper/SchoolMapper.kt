package com.meals.data.mapper

import com.meals.data.database.entity.SchoolEntity
import com.meals.domain.model.SchoolInfo

fun SchoolInfo.mapToEntity(): SchoolEntity = SchoolEntity(
    0,
    this.school_name,
    this.school_locate,
    this.office_code,
    this.school_id
)

fun SchoolEntity.mapToModel(): SchoolInfo = SchoolInfo(
    this.name,
    this.locate,
    this.code,
    this.id
)