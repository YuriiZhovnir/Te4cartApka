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
import com.example.te4cartapka.network.respons.DeteilsProduct
import com.example.te4cartapka.presenter.ProductDetailsPresenter
import com.example.te4cartapka.presenter.impl.ProductDetailsPresenterImpl
import com.example.te4cartapka.view.ProductDetailsView
import kotlinx.android.synthetic.main.fragment__product_deteils.*
import kotlinx.android.synthetic.main.fragment__product_deteils.nameProduct
import com.google.android.material.tabs.TabLayout


class DeteilsProductFragment : Fragment(), ProductDetailsView {
    companion object {
        const val PRODUCT_ID = "pattern_id"
        fun newInstance(productId: Int) = DeteilsProductFragment().apply {
            arguments = Bundle(3).apply {
                putInt(PRODUCT_ID, productId)
            }
        }
    }

    private var presenter: ProductDetailsPresenter? = null

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

        if (null == presenter) {
            presenter = ProductDetailsPresenterImpl(this)
        }
        productId?.let { presenter?.getProductDetailsById(it) }
    }

    override fun showProductDetails(deteilsProduct: DeteilsProduct) {
        nameProduct.text = deteilsProduct.productName
        val adapter = context?.let { SliderAdapter(it, deteilsProduct.images) }
        viewPager.setAdapter(adapter)


        val tabLayout = view?.findViewById(com.example.te4cartapka.R.id.tab_layout) as TabLayout
        tabLayout.setupWithViewPager(viewPager, true)



        priceProduct.text = deteilsProduct.productPrice.toString()


        if (deteilsProduct.available == true) {
            productExistence.text = getText(R.string.product_exist)
        }
        nameCategories.text = deteilsProduct.category.categoryName

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shortDescription.setText(Html.fromHtml(deteilsProduct.description, Html.FROM_HTML_MODE_COMPACT))
            description.setText(Html.fromHtml(deteilsProduct.description, Html.FROM_HTML_MODE_COMPACT))
        } else {
            shortDescription.setText(Html.fromHtml(deteilsProduct.description))
            description.setText(Html.fromHtml(deteilsProduct.description))
        }

        openDescription.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                description.setText(Html.fromHtml(deteilsProduct.description, Html.FROM_HTML_MODE_COMPACT))
            } else {
                description.setText(Html.fromHtml(deteilsProduct.description))
            }
            openDescription.setTextColor(Color.RED)
            parametersProduct.setTextColor(Color.BLACK)
            description.visibility = View.VISIBLE
            allParameters.visibility = View.GONE

        }
        parametersProduct.setOnClickListener {
            //                            characteristics = response.productCategoryProperty
            parametersProduct.setTextColor(Color.RED)
            openDescription.setTextColor(Color.BLACK)
            description.visibility = View.GONE
            allParameters.visibility = View.VISIBLE
            var properties = 0

            deteilsProduct.productCategoryProperty.simpleProperties?.forEach {
                val property = deteilsProduct.productCategoryProperty.simpleProperties
                if (it.value.isNullOrEmpty()) {


                }

            }

        }

    }

}
