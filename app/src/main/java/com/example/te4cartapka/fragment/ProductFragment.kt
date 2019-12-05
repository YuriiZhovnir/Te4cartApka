package com.example.te4cartapka.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.te4cartapka.R
import com.example.te4cartapka.adapter.ProductAdapter
import com.example.te4cartapka.helpers.GlobalData
import com.example.te4cartapka.helpers.GlobalData.product
import com.example.te4cartapka.network.respons.GetProductFromCategoryNoRand
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import kotlinx.android.synthetic.main.fragment_product.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProductFragment : Fragment(), ProductAdapter.OnItemClick {

    companion object {
        //        fun newInstance(): ProductFragment {
//            return ProductFragment()
//        }
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
        slug?.let { getProductCategory(it) }
    }


    fun getProductCategory(slug: String) {
        GlobalData.apiService?.getProductFromCategoryNoRand(slug)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<ArrayList<GetProductFromCategoryNoRand>>() {
                    override fun onNext(result: ArrayList<GetProductFromCategoryNoRand>) {
                        productListC.adapter = ProductAdapter(this@ProductFragment, result)

                    }
                })
    }

    override fun onClickProduct(getProductFromCategoryNoRand: GetProductFromCategoryNoRand) {
        val fragment = DeteilsProductFragment.newInstance(getProductFromCategoryNoRand.id?.toInt()!!)
        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }
}
