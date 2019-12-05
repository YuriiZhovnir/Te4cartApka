package com.example.te4cartapka.network.respons

import com.google.gson.annotations.SerializedName

class Categories(@SerializedName("categoryName") var categoryName: String?,
                 @SerializedName("id") var id: Int?,
                 @SerializedName("image") var image: String?,
                 @SerializedName("keywords")var keywords: String?,
                 @SerializedName("seoText") var seoText: String?,
                 @SerializedName("slug") var slug: String?,
                 var products : ArrayList<Product> = ArrayList(),
                 var getProductFromCategoryNoRand : ArrayList<GetProductFromCategoryNoRand> = ArrayList()
//                 var getProductFromCategoryNoRand: GetProductFromCategoryNoRand
                 )