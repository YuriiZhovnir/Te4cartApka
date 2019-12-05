package com.example.te4cartapka.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.te4cartapka.R
import com.example.te4cartapka.network.respons.GetProductFromCategoryNoRand
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item_style.view.*

class ProductAdapter(val listener: ProductAdapter.OnItemClick, var product: ArrayList<GetProductFromCategoryNoRand> = ArrayList())
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_style, parent, false)
        return ProductAdapter.ViewHolder(view)
    }

    override fun getItemCount() = product.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = product.get(position)
        holder.name.text = item.productName
        Picasso.get()
                .load("https://te4cart.com.ua/files/" + item.mainImage)
                .into(holder.image)
        holder.price.text = item.productPrice.toString()
        holder.productItem.setOnClickListener {
            listener?.onClickProduct(item)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.nameProduct
        val image = itemView.imageProduct
        val price = itemView.price
        val productItem = itemView.productItem

    }

    interface OnItemClick {
        fun onClickProduct(getProductFromCategoryNoRand: GetProductFromCategoryNoRand)
    }

}