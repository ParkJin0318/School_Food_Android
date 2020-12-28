package com.meals.school_food.widget.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meals.school_food.widget.adapter.RecyclerItem
import com.meals.school_food.widget.adapter.RecyclerViewAdapter

@BindingAdapter("recyclerItems")
fun RecyclerView.setRecyclerViewItems(
        items: List<RecyclerItem>?
) {
    if (adapter == null) {
        adapter = RecyclerViewAdapter()
        layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as? RecyclerViewAdapter)?.updateItem(it) }
}