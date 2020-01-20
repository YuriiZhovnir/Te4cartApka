package com.example.te4cartapka.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.te4cartapka.R
import com.example.te4cartapka.adapter.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_product.*


class ProductFragment : Fragment(), ProductsAdapter.OnItemClick {

    companion object {
        const val PRODUCT_ID = "pattern_id"

        fun newInstance(slug: String) = ProductFragment().apply {
            arguments = Bundle(3).apply {
                putString(PRODUCT_ID, slug)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        productListC?.layoutManager = linearLayoutManager
        val slug = arguments?.getString(ProductFragment.PRODUCT_ID)
//        slug?.let { getProductCategory(it) }

    }

    override fun onItemClick(productId: Int) {
        val fragment = DeteilsProductFragment.newInstance(productId)
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }


//    fun getProductCategory(slug: String) {
//        GlobalData.apiService?.getProductFromCategoryNoRand(slug)
//                ?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.unsubscribeOn(Schedulers.io())
//                ?.subscribe(object : RetrofitSubscriber<ArrayList<GetProductFromCategoryNoRand>>() {
//                    override fun onNext(respons: ArrayList<GetProductFromCategoryNoRand>) {
////                        productListC.adapter = ProductsAdapter(this@ProductFragment, respons)
//
//                    }
//                })
    }

