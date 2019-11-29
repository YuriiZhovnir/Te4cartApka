package com.example.te4cartapka.helpers

import com.example.te4cartapka.network.Api
import com.example.te4cartapka.network.respons.Categories
import com.example.te4cartapka.network.respons.DeteilsProduct
import com.example.te4cartapka.network.respons.Product
import jdroidcoder.ua.apiservice.initializer.ApiServiceInitializer

object GlobalData{
    var caregoris: Categories? = null
    var product: Product? = null
    var productDetails: DeteilsProduct? = null

    val apiService: Api? =
            ApiServiceInitializer.init("https://te4cart.com.ua/")?.create(Api::class.java)
}