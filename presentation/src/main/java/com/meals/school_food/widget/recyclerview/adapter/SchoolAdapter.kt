package com.meals.school_food.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.meals.domain.model.DetailSearch
import com.meals.school_food.R
import com.meals.school_food.databinding.ItemSchoolBinding
import com.meals.school_food.widget.recyclerview.viewmodel.SchoolItemViewModel

class SchoolAdapter : RecyclerView.Adapter<SchoolAdapter.ViewHolder>(){

    private lateinit var schoolList : ArrayList<DetailSearch>
    val click = MutableLiveData<Int>()

    fun setList(list : ArrayList<DetailSearch>) {
        if(::schoolList.isInitialized) return
        schoolList = list
    }

    class ViewHolder(private val binding : ItemSchoolBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = SchoolItemViewModel()

        fun bind(detailSearch: DetailSearch) {
            binding.teName.isSelected = true
            binding.teAddress.isSelected = true

            viewModel.bind(detailSearch)
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_school,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if(::schoolList.isInitialized) schoolList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder) {
            bind(schoolList[position])
            itemView.setOnClickListener {
                click.value = position
            }
        }
    }
}