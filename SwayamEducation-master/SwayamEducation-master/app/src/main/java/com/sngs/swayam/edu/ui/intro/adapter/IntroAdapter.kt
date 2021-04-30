package com.sngs.swayam.edu.ui.intro.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Intro
import com.sngs.swayam.edu.databinding.ActivityDashboardListBinding
import com.sngs.swayam.edu.ui.dashboard.adapter.DashboardAdapter

class IntroAdapter() : PagerAdapter() {

    var arrayList: List<Intro>? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    fun setData(list: ArrayList<Intro>){
        arrayList = list
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(container.context).inflate(R.layout.layout_intro_screen,
                container, false)

        (view.findViewById(R.id.img) as ImageView).setImageResource(arrayList!!.get(position).img!!)
        (view.findViewById(R.id.tvHeading) as TextView).text = arrayList!!.get(position).header_text

        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return arrayList?.size ?: 0
    }

}