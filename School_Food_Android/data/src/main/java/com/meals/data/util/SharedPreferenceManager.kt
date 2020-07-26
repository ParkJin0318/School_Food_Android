package com.meals.data.util

import android.content.Context
import android.content.SharedPreferences
import com.meals.domain.model.SchoolInformation

object SharedPreferenceManager {
    private const val PREF_SCHOOL_ID = "school_id"
    private const val PREF_OFFICE_CODE = "office_code"
    private const val PREF_SCHOOL_NAME = "school_name"
    private const val PREF_SCHOOL_ADDRESS = "school_address"

    fun setSchoolInformation(context: Context, school: SchoolInformation) {
        with(getDefaultSharedPreferences(context).edit()) {
            putString(PREF_SCHOOL_ID, school.school_id).apply()
            putString(PREF_OFFICE_CODE, school.office_code).apply()
            putString(PREF_SCHOOL_NAME, school.school_name).apply()
            putString(PREF_SCHOOL_ADDRESS, school.school_locate).apply()
        }
    }

    fun getSchoolId(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_SCHOOL_ID, null)
    }

    fun getOfficeCode(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_OFFICE_CODE, null)
    }

    fun getSchoolName(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_SCHOOL_NAME, null)
    }

    fun getSchoolAddress(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_SCHOOL_ADDRESS, null)
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            getDefaultSharedPreferencesName(context),
            getDefaultSharedPreferencesMode()
        )
    }

    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName.toString() + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }
}