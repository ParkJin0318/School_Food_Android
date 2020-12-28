package com.meals.school_food.view.fragment

import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentMenuBinding
import com.meals.school_food.view.activity.SearchActivity
import com.meals.school_food.view.activity.VersionActivity
import com.meals.school_food.viewmodel.MenuViewModel
import com.meals.school_food.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel
        get() = getViewModel(MenuViewModel::class)

    override val layoutRes: Int
        get() = R.layout.fragment_menu

    override fun observerViewModel() {
        with(viewModel) {
            schoolChangeEvent.observe(::getLifecycle) {
                startActivity(SearchActivity::class.java)
            }
            versionEvent.observe(::getLifecycle) {
                startActivity(VersionActivity::class.java)
            }
        }
    }
}