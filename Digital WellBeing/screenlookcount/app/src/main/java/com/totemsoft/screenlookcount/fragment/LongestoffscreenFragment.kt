package com.totemsoft.screenlookcount.fragment.main

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.annotation.RequiresApi
import com.totemsoft.screenlookcount.BaseFragment
import com.totemsoft.screenlookcount.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries
import java.lang.Exception
import java.util.*



class LongestoffscreenFragment : BaseFragment(), MainContract.View{

    private lateinit var currentView: View
    private lateinit var presenter: MainPresenter
    val MyPREFERENCES = "MyPrefs"
    var sharedpreferences: SharedPreferences? = null
    var firstuse=0.toLong()

    override fun getContentResource() = R.layout.fragment_longestoffscreen

    override fun init(view: View, state: Bundle?) {
        currentView = view
        presenter = MainPresenter()
        presenter.attach(this)

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        firstuse = preferences.getLong("init", 0.toLong())

        if(firstuse==0.toLong()){


            firstuse=System.currentTimeMillis()

            sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
            val editor = sharedpreferences?.edit()
            editor?.putLong("init", firstuse)
            editor?.apply()

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        context?.let {
            val nowDateString = Date()

            presenter.setCountersValues(it, nowDateString, "longest")

        }

    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray) {

    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray,arr_1:IntArray) {

        var date=Date(firstuse)
        if(System.currentTimeMillis()>(date.time+604800000)){

            var total= (7*86400-looks!!.toInt())

            currentView.total.text =total.toString()
            currentView.dailyaverage.text=(total/7).toString()

        }
        else{

            var total=  ((System.currentTimeMillis()-date.time)/1000)-looks!!.toInt()

            currentView.total.text =total.toString()
            currentView.dailyaverage.text=(total/7).toString()

        }

        currentView.max.text=max.toString()
        currentView.min.text=min.toString()


        val series = ValueLineSeries()
        series.color = -0xa9480f

        var c=1;

        series.addPoint(ValueLinePoint("Day " + 1, arr_1[6].toFloat()))
        series.addPoint(ValueLinePoint("Day " + 2, arr_1[5].toFloat()))
        series.addPoint(ValueLinePoint("Day " + 3, arr_1[4].toFloat()))
        series.addPoint(ValueLinePoint("Day " + 4, arr_1[3].toFloat()))
        series.addPoint(ValueLinePoint("Day " + 5, arr_1[2].toFloat()))
        series.addPoint(ValueLinePoint("Day " + 6, arr_1[1].toFloat()))
        series.addPoint(ValueLinePoint("Day " + 7, arr_1[0].toFloat()))



        var mCubicValueLineChart =currentView.cubiclinechart as ValueLineChart
        mCubicValueLineChart.addSeries(series)
        mCubicValueLineChart.startAnimation()

    }

    override fun setCountersView(looks: Int?, type: String?, min: Long, max: Long, arr: LongArray) {
        TODO("Not yet implemented")
    }

    fun getMainPresenter() = presenter
}