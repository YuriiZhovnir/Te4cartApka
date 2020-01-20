package com.example.te4carta

import com.example.te4cartapka.network.respons.Categories
import com.example.te4cartapka.network.respons.Product

interface InterfaceView {
    fun setCategories(categoriesIV: ArrayList<Categories>)
    fun setProduct(product: ArrayList<Product>,slug: String, categoriesName: String)

}