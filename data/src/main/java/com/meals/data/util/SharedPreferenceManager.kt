package com.meals.data.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {
    private const val PREF_SCHOOL_ID = "school_id"
    private const val PREF_SCHOOL_NAME = "school_name"

    fun setSchoolId(context: Context, schoolId: String) {
        getDefaultSharedPreferences(context).edit().putString(PREF_SCHOOL_ID, schoolId).apply()
    }

    fun deleteSchoolId(context: Context) {
        getDefaultSharedPreferences(context).edit().remove(PREF_SCHOOL_ID).apply()
    }

    fun getSchoolId(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_SCHOOL_ID, null)
    }

    fun setSchoolName(context: Context, schoolName: String) {
        getDefaultSharedPreferences(context).edit().putString(PREF_SCHOOL_NAME, schoolName).apply()
    }

    fun deleteSchoolName(context: Context) {
        getDefaultSharedPreferences(context).edit().remove(PREF_SCHOOL_NAME).apply()
    }

    fun getSchoolName(context: Context): String? {
        return getDefaultSharedPreferences(context).getString(PREF_SCHOOL_NAME, null)
    }

    fun getDefaultSharedPreferences(context: Context): SharedPreferences {
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