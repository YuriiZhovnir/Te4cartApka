package com.example.te4cartapka.view

import com.example.te4cartapka.network.respons.DeteilsProduct

interface ProductDetailsView {
    fun showProductDetails(product: DeteilsProduct)
}