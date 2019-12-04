package com.example.te4cartapka.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class SliderAdapter internal constructor(private val context: Context, private val imageUrls: Array<String>) : PagerAdapter() {

    override fun getCount(): Int {
            return imageUrls.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        Picasso.get()
                .load(imageUrls?.get(position))
                .fit()
                .centerCrop()
                .into(imageView)
        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}