package com.example.te4cartapka.network

import com.example.te4cartapka.network.respons.Categories
import com.example.te4cartapka.network.respons.DeteilsProduct
import com.example.te4cartapka.network.respons.Product
import retrofit2.http.*
import rx.Observable
import kotlin.collections.ArrayList

interface Api{
    @POST("getCategories")
    fun getCategories(): Observable<ArrayList<Categories>>

    @POST("getProductFromCategory")
    @FormUrlEncoded
    fun getProduct(@Field("categorySlug") categorySlug: String): Observable<ArrayList<Product>>

    @GET("getProductDetails")
    fun getDetailsProduct(@Query("productId") productId: Int): Observable <DeteilsProduct>
}