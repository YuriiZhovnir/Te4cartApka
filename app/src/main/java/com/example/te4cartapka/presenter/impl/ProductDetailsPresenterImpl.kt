package com.example.te4cartapka.presenter.impl

import com.example.te4cartapka.helpers.GlobalData
import com.example.te4cartapka.network.respons.DeteilsProduct
import com.example.te4cartapka.presenter.ProductDetailsPresenter
import com.example.te4cartapka.view.ProductDetailsView
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProductDetailsPresenterImpl(val view:ProductDetailsView? = null):ProductDetailsPresenter{
    override fun getProductDetailsById(productId: Int) {
        GlobalData.apiService?.getDetailsProduct(productId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<DeteilsProduct>() {
                    override fun onNext(response: DeteilsProduct) {
                        view?.showProductDetails(response)
                    }
                })
    }
}