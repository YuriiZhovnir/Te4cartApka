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
        @SerializedName("id") var id: Int?,
        @SerializedName("simpleProperties") var simpleProperties: ArrayList<SimpleProperties>? = ArrayList(),
        @SerializedName("advancedProperties") var advancedProperties: ArrayList<AdvancedProperties>? = ArrayList()
)

class SimpleProperties(
        @SerializedName("id") var id: Int?,
        @SerializedName("propertyName") var propertyName: String?,
        @SerializedName("parentId") var parentId: Int?,
        @SerializedName("value") var value: String?,
        @SerializedName("group") var group: Int?
        )
class AdvancedProperties(
        @SerializedName("id") var id: Int?,
        @SerializedName("propertyName") var propertyName: String?,
        @SerializedName("parentId") var parentId: Int?,
        @SerializedName("value") var value: String?,
        @SerializedName("group") var group: Int?
        )