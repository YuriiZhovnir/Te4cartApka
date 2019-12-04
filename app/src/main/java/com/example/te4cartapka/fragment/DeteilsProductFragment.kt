package com.example.te4cartapka.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.te4cartapka.R
import com.example.te4cartapka.adapter.SliderAdapter
import com.example.te4cartapka.helpers.GlobalData
import com.example.te4cartapka.network.respons.DeteilsProduct
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import kotlinx.android.synthetic.main.fragment__product_deteils.*
import kotlinx.android.synthetic.main.fragment__product_deteils.nameProduct
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import com.google.android.material.tabs.TabLayout




class DeteilsProductFragment : Fragment() {
    companion object {
        const val PRODUCT_ID = "pattern_id"
        fun newInstance(productId: Int) = DeteilsProductFragment().apply {
            arguments = Bundle(3).apply {
                putInt(PRODUCT_ID, productId)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment__product_deteils, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = arguments?.getInt(PRODUCT_ID)
        productId?.let { getDeteilsProduct(it) }
    }

    fun getDeteilsProduct(productId: Int) {
        GlobalData.apiService?.getDetailsProduct(productId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber<DeteilsProduct>() {
                    override fun onNext(response: DeteilsProduct) {
                        nameProduct.text = response.productName

                        val adapter = context?.let { SliderAdapter(it, response.images) }
                        viewPager.setAdapter(adapter)


                        val tabLayout = view?.findViewById(R.id.tab_layout) as TabLayout
                        tabLayout.setupWithViewPager(viewPager, true)



                        priceProduct.text = response.productPrice.toString()


                        if (response.available == true) {
                            productExistence.text = getText(R.string.product_exist)
                        }
                        nameCategories.text = response.category.categoryName

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            shortDescription.setText(Html.fromHtml(response.description, Html.FROM_HTML_MODE_COMPACT))
                            description.setText(Html.fromHtml(response.description, Html.FROM_HTML_MODE_COMPACT))
                        } else {
                            shortDescription.setText(Html.fromHtml(response.description))
                            description.setText(Html.fromHtml(response.description))
                        }

                        openDescription.setOnClickListener {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                description.setText(Html.fromHtml(response.description, Html.FROM_HTML_MODE_COMPACT))
                            } else {
                                description.setText(Html.fromHtml(response.description))
                            }
                            openDescription.setTextColor(Color.RED)
                            parametersProduct.setTextColor(Color.BLACK)

                        }
                        parametersProduct.setOnClickListener {
//                            characteristics = response.productCategoryProperty
                            parametersProduct.setTextColor(Color.RED)
                            openDescription.setTextColor(Color.BLACK)
                        }

                    }
                })
    }

}