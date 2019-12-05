package com.example.te4cartapka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.te4cartapka.R
import com.example.te4cartapka.network.respons.Categories
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.categories_item_style.view.*
import kotlinx.android.synthetic.main.product_recycler.view.*

class CategoriesAdapter(var categories: ArrayList<Categories> = ArrayList(), val listener : OnItemClick?) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_item_style, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories.get(position)
        holder.name.text = item.categoryName
        Picasso.get()
            .load("https://te4cart.com.ua/files/" + item.image)
            .into(holder.imageC)
        holder.categoriesItem.setOnClickListener {
            listener?.onItemClickCategory(item)
        }
    }

    override fun getItemCount() = categories.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.nameCategoriesList
        val imageC = itemView.imageCategoriesList
        val categoriesItem = itemView.categoriesItemClick
    }

    interface OnItemClick {
        fun onItemClickCategory(categories: Categories)
    }
}


