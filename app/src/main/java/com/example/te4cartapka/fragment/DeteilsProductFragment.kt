package com.example.te4cartapka.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.te4cartapka.R
import com.example.te4cartapka.helpers.GlobalData
import com.example.te4cartapka.network.respons.DeteilsProduct
import com.example.te4cartapka.network.respons.Product
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import kotlinx.android.synthetic.main.fragment__product_deteils.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.CacheResponse

class DeteilsProductFragment : Fragment() {
    companion object {
        //        const val PRODUCT_ID = "product_id"
        const val PRODUCT_ID = "pattern_id"

        fun newInstance(productId: String) = DeteilsProductFragment().apply {
            arguments = Bundle(3).apply {
                putString(PRODUCT_ID, productId)
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
        val productId = arguments?.getString(PRODUCT_ID)
        productId?.let { getDeteilsProduct(it) }
//        getDeteilsProduct()
    }

    fun getDeteilsProduct(productId: String) {
        GlobalData.apiService?.getDetailsProduct(productId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.unsubscribeOn(Schedulers.io())
                ?.subscribe(object : RetrofitSubscriber <DeteilsProduct>() {
                    override fun onNext(result: DeteilsProduct) {
                        super.onNext(result)
                    }
                })
    }

}