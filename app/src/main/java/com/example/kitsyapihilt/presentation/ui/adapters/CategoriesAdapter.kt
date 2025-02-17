package com.example.kitsyapihilt.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.kitsyapihilt.databinding.ItemCategoriesBinding
import com.example.kitsyapihilt.presentation.models.categories.DataItemCtUI

class CategoriesAdapter(
    private var categoriesList: List<DataItemCtUI>
) : Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val selectedItems = arrayListOf<String>()

    fun getSelectedItems(): ArrayList<String> {
        return selectedItems
    }

    fun clearSelectedItems() {
        selectedItems.clear()
        categoriesList.forEach {
            it.attributes.isClick = false
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: List<DataItemCtUI>) {
        this.categoriesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount() = categoriesList.size

    inner class CategoriesViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val data = categoriesList[position]
            binding.chBox.text = data.attributes.title
            binding.chBox.setOnCheckedChangeListener(null)
            binding.chBox.isChecked = data.attributes.isClick
            binding.chBox.setOnCheckedChangeListener { _, isChecked ->
                data.attributes.isClick = isChecked
                if (data.attributes.isClick) {
                    selectedItems.add(data.attributes.title)
                } else {
                    selectedItems.remove(data.attributes.title)
                }
            }
        }
    }
}