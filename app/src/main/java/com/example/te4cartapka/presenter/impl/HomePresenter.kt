package com.example.te4cartapka.presenter.impl

import com.example.te4carta.InterfaceView
import com.example.te4cartapka.helpers.GlobalData
import com.example.te4cartapka.network.respons.Categories
import com.example.te4cartapka.network.respons.Product
import com.example.te4cartapka.presenter.InterfacePresenter
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class HomePresenter(var view: InterfaceView? = null) : InterfacePresenter {


    override fun getCategories() {
        GlobalData.apiService?.getCategories()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<ArrayList<Categories>>() {
                    override fun onNext(response: ArrayList<Categories>) {
                        view?.setCategories(response)
                        response.forEach {
                            it.slug?.let { it1 -> it.categoryName?.let { it2 -> getProduct(null.toString(), it1, it2, false) } }
                        }
                    }
                })
    }

    override fun getProduct(searchText: String?, slug: String, categoriesName: String, isSearch: Boolean) {
        val request = if (isSearch) {
            GlobalData.apiService?.getSearch(searchText.toString())
        } else {
            GlobalData.apiService?.getProduct(slug)
        }
        request?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<ArrayList<Product>>() {
                    override fun onNext(response: ArrayList<Product>) {
                        view?.setProduct(response, slug, categoriesName)
                    }
                })
    }
}
