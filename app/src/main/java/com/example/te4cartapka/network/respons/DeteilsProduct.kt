package com.example.te4cartapka.network.respons

import android.media.Image
import com.google.gson.annotations.SerializedName

class DeteilsProduct(
        @SerializedName("available") var available: Boolean = false,
        @SerializedName("description") var description: String?,
        @SerializedName("id") var id: Int?,
        @SerializedName("images") var images: ArrayList<String>? = ArrayList(),
        var productCategoryProperty: ProductCategoryProperty,
        @SerializedName("productName") var productName: String?,
        @SerializedName("productPrice") var productPrice: Int?,
        var category: Category
//                     @SerializedName("productTotalPrice") var productTotalPrice: Int?
)

class Category(@SerializedName("categoryName") var categoryName: String?)

class ProductCategoryProperty(
//        @SerializedName("advancedProperties") var advancedProperties: ArrayList<String>? = ArrayList()
)