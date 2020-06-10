package com.meals.school_food.view.fragment

import androidx.lifecycle.Observer
import com.meals.school_food.R
import com.meals.school_food.base.BaseFragment
import com.meals.school_food.databinding.FragmentAddressBinding
import com.meals.school_food.viewmodel.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AddressFragment : BaseFragment<FragmentAddressBinding, AddressViewModel>() {

    override val viewModel: AddressViewModel
        get() = getViewModel(AddressViewModel::class)

    override val layoutRes: Int
        get() = R.layout.fragment_address

    override fun observerViewModel() {
        with(viewModel) {
            check.observe(this@AddressFragment, Observer {
                string.value = schoolList[0].schools[0].school_name
            })
        }
    }
}