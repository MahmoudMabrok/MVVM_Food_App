package com.af.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.af.foodapp.data.source.remote.model.Category
import com.af.foodapp.databinding.CategoryItemBinding
import com.bumptech.glide.Glide

//this adapter is used to display the categories of food in home screen
class CategoriesListAdapter :
    ListAdapter<Category, CategoriesListAdapter.CategoriesViewHolder>(DiffCallback()) {

    inner class CategoriesViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var categoriesList = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null
    private val differ = AsyncListDiffer(this, DiffCallback())

    //this is used to set the category list in recycler view
    fun setCategories(categoriesList: List<Category>) {
        this.categoriesList = categoriesList as ArrayList
        differ.submitList(categoriesList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        // I used only 12 because there is a problem with the last two items _pictures_ so i ignored them
        return categoriesList.size.coerceAtMost(12)
    }

    override fun onBindViewHolder(
        holder: CategoriesListAdapter.CategoriesViewHolder,
        position: Int
    ) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = categoriesList[position].strCategory

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(categoriesList[position])
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.strCategory == newItem.strCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}

