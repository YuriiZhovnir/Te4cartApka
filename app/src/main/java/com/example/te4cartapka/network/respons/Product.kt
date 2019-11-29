package com.example.te4cartapka.network.respons

import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("id") var id: Int?,
    @SerializedName("productName") var productName: String?,
    @SerializedName("mainImage") var mainImage: String?,
    @SerializedName("productPrice") var productPrice: Int?,
    var DeteilsProduct : DeteilsProduct)

