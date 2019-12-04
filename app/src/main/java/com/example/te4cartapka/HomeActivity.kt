package com.example.te4cartapka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.te4cartapka.adapter.CategoriesAdapter
import com.example.te4cartapka.adapter.ProductAdapter
import com.example.te4cartapka.fragment.DeteilsProductFragment
import com.example.te4cartapka.helpers.GlobalData
import com.example.te4cartapka.network.respons.Categories
import com.example.te4cartapka.network.respons.Product
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeActivity : AppCompatActivity(), ProductAdapter.OnItemClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayoutManager = LinearLayoutManager(this)
        categoriesList?.layoutManager = linearLayoutManager
        getCategories()
    }

    fun getCategories() {
        GlobalData.apiService?.getCategories()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<ArrayList<Categories>>() {
                    override fun onNext(response: ArrayList<Categories>) {
                        categoriesList?.adapter = CategoriesAdapter(response)
                        response.forEach {
                            getProduct(it.slug.toString(), it.categoryName.toString())
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
    }

    fun getProduct(slug: String, categoriesName: String) {
        GlobalData.apiService?.getProduct(slug)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<ArrayList<Product>>() {
                    override fun onNext(respons: ArrayList<Product>) {

                        val view = LayoutInflater.from(this@HomeActivity)
                                .inflate(R.layout.product_recycler, null)

                        view?.findViewById<TextView>(R.id.categoriesNameInProductList)?.apply {
                            this?.text = categoriesName
                        }
                        view?.findViewById<RecyclerView>(R.id.productList)?.apply {
                            this?.layoutManager = LinearLayoutManager(
                                    this@HomeActivity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                            )
                            this?.adapter = ProductAdapter(this@HomeActivity, respons)

                        }
                        products.addView(view)
                    }
                })
    }

    override fun onItemClick(product: Product) {
        val fragment = DeteilsProductFragment.newInstance(product.id?.toInt()!!)
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }

}
