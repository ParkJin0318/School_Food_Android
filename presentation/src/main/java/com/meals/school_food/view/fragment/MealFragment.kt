package com.meals.school_food.view.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentMealBinding
import com.meals.school_food.viewmodel.MealViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.Calendar
import java.util.TimeZone

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {

    override val viewModel: MealViewModel
        get() = getViewModel(MealViewModel::class)

    override val layoutRes: Int
        get() = R.layout.fragment_meal

    override fun observerViewModel() {
        with(viewModel) {
            dateEvent.observe(this@MealFragment, Observer {
                val cal: Calendar = Calendar.getInstance(TimeZone.getDefault())

                DatePickerDialog(requireContext(), OnDateSetListener { view, year, month, day ->
                        setDate(year, month + 1, day)
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
            })
        }
    }
}