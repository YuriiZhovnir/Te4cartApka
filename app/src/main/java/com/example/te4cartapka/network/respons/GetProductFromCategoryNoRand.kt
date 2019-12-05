package com.example.te4cartapka.network.respons

import com.google.gson.annotations.SerializedName

class GetProductFromCategoryNoRand (
        @SerializedName("id") var id: Int?,
        @SerializedName("mainImage") var mainImage: String?,
        @SerializedName("productPrice") var productPrice: Int?,
        @SerializedName("productName") var productName: String?
        )