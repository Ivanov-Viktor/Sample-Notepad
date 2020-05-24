package com.example.notesapp.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Category
import kotlinx.android.synthetic.main.category_item.view.*


class CategoriesAdapter(
    private val deleteCategory: (Category) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    private val categories: MutableList<Category> = mutableListOf()

    fun addCategories(newCategories: List<Category>) {
        val startIndex = categories.size
        categories.addAll(newCategories)
        notifyItemRangeInserted(startIndex, newCategories.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categories[position]) {
            deleteCategory(it) // удаляем из БД
            categories.removeAt(position) // удаляем из списка ресайклера
            notifyItemRemoved(position) // инициируем перерисовку
            notifyItemRangeChanged(position, itemCount)
        }
    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category, deleteCategory: (Category) -> Unit) = itemView.apply {
            categoryName.text = category.name

            buttonDeleteCategory.setOnClickListener {
                deleteCategory(category)
            }
        }

    }
}