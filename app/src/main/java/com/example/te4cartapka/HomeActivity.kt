package com.example.te4cartapka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.te4carta.InterfaceView
import com.example.te4cartapka.adapter.CategoriesAdapter
import com.example.te4cartapka.adapter.ProductsAdapter
import com.example.te4cartapka.fragment.DeteilsProductFragment
import com.example.te4cartapka.fragment.ProductFragment
import com.example.te4cartapka.network.respons.Categories
import com.example.te4cartapka.network.respons.Product
import com.example.te4cartapka.presenter.InterfacePresenter
import com.example.te4cartapka.presenter.impl.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar.*


class HomeActivity : AppCompatActivity(), ProductsAdapter.OnItemClick,
        CategoriesAdapter.OnItemClick, InterfaceView {

    private var categ: InterfacePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (null == categ) {
            categ = HomePresenter(this)
        }

        val linearLayoutManager = LinearLayoutManager(this)
        categoriesList?.layoutManager = linearLayoutManager

        val searchText = searchET as EditText
        searchBTN.setOnClickListener{
            categ?.getProduct(searchText.text.toString(), null.toString(), null.toString(), true)
        }

        categ?.getCategories()
        categ?.getProduct(null.toString(), slug = String(), categoriesName = String(), isSearch = false)

    }

    override fun setCategories(categIV: ArrayList<Categories>) {
        categoriesList.adapter = CategoriesAdapter(categIV, this@HomeActivity)

    }

    override fun setProduct(product: ArrayList<Product>, slug: String, categoriesName: String) {
        val view = LayoutInflater.from(this@HomeActivity)
                .inflate(R.layout.product_recycler, null)
        view?.findViewById<TextView>(R.id.categoriesNameInProductList)?.apply {
            this.text = categoriesName
        }

        view?.findViewById<RecyclerView>(R.id.productList)?.apply {
            this?.layoutManager = LinearLayoutManager(
                    this@HomeActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
            this?.adapter = ProductsAdapter(this@HomeActivity, product)

        }
        products.addView(view)

        val viewt = LayoutInflater.from(this@HomeActivity)
                .inflate(R.layout.search_fragment, null)
        viewt?.findViewById<RecyclerView>(R.id.searchList)?.apply {
            this?.layoutManager = LinearLayoutManager(
                    this@HomeActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
            this?.adapter = ProductsAdapter(this@HomeActivity, product)

        }
//        searchList.adapter = ProductsAdapter(this@HomeActivity, product)

    }


    override fun onItemClick(productId: Int) {
        val fragment = DeteilsProductFragment.newInstance(productId)
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun onItemClickCategory(categories: Categories) {
        val fragment = ProductFragment.newInstance(categories.slug.toString())
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }


}

