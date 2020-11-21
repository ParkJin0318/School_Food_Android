package com.meals.domain.model

class MealInfo(
    breakfast: String?,
    lunch: String?,
    dinner: String?
) {

    var breakfast: String? = breakfast
        get() {
            return filterMeal(field)
        }

    var lunch: String? = lunch
        get() {
            return filterMeal(field)
        }

    var dinner: String? = dinner
        get() {
            return filterMeal(field)
        }

    private fun filterMeal(mealRaw: String?): String? {
        val lines = mealRaw!!.split("<br/>").toTypedArray()
        val stringBuilder = StringBuilder()
        for (i in lines.indices) {
            val line = lines[i]
            if (i == lines.size - 1) stringBuilder.append(line) else stringBuilder.append(line).append(
                "\n"
            )
        }
        return stringBuilder.toString()
    }
}