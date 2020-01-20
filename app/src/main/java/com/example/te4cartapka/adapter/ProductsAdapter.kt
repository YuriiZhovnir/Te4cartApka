package com.example.te4cartapka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.te4cartapka.R
import com.example.te4cartapka.network.respons.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item_style.view.*

class ProductsAdapter(val listener: OnItemClick?, var product: ArrayList<Product> = ArrayList()) :
        RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_style, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = product.get(position)
        holder.name.text = item.productName
        Picasso.get()
                .load("https://te4cart.com.ua/files/" + if (false == item.mainImage?.isNullOrEmpty()) {
                    item.mainImage
                } else {
                    item?.images?.get(0)
                })
                .into(holder.image)
        holder.price.text = item.productPrice.toString()
        holder.productItem.setOnClickListener {
            item.id?.let { it1 -> listener?.onItemClick(it1) }
        }
    }

    override fun getItemCount() = product.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.nameProduct
        val image = itemView.imageProduct
        val price = itemView.price
        val productItem = itemView.productItem
    }

    interface OnItemClick {
        fun onItemClick(productId: Int)
    }
}