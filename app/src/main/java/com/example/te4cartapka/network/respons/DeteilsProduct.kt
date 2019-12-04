package com.example.te4cartapka.network.respons

import android.media.Image
import com.google.gson.annotations.SerializedName
class DeteilsProduct(
        @SerializedName("available") var available: Boolean = false,
//                      var Category: Category,
        @SerializedName("description") var description: String?,
        @SerializedName("id") var id: Int?,
        @SerializedName("images") var images: ArrayList<String>? = ArrayList(),
//                     @SerializedName("keywords") var keywords: String?,
//                     @SerializedName("productArticul") var productArticul: String?,
//                     @SerializedName("productCategoryProperty") var productCategoryProperty: String?,
//                     @SerializedName("productCount") var productCount: String?,
//                     @SerializedName("productExtra") var productExtra: String?,
//                     @SerializedName("productModel") var productModel: String?,
        @SerializedName("productName") var productName: String?,
        @SerializedName("productPrice") var productPrice: Int?,
        var category: Category
//                     @SerializedName("productTotalPrice") var productTotalPrice: Int?
)