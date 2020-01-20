package com.example.te4cartapka.presenter

interface InterfacePresenter {
    fun getCategories()
    fun getProduct(searchText: String?, slug: String, categoriesName: String, isSearch: Boolean)
}