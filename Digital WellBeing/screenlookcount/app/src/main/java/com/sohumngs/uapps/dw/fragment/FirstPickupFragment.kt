package com.sohumngs.uapps.dw.fragment.main

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.sohumngs.uapps.dw.BaseFragment
import com.sohumngs.uapps.R
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries
import java.util.*



class FirstPickupFragment : BaseFragment(), MainContract.View{

    private lateinit var currentView: View
    private lateinit var presenter: MainPresenter
    val MyPREFERENCES = "MyPrefs"
    var sharedpreferences: SharedPreferences? = null
    var firstuse=0.toLong()

    override fun getContentResource() = R.layout.fragment_first_pickup

    override fun init(view: View, state: Bundle?) {
        currentView = view
        presenter = MainPresenter()
        presenter.attach(this)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        context?.let {
            val nowDateString = Date()

            presenter.setCountersValues(it, nowDateString, "firstpickup")

        }

    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray) {
        TODO("Not yet implemented")
    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray, arr_1: IntArray) {
        TODO("Not yet implemented")
    }


    override fun setCountersView(looks: Int?, type: String?, min: Long, max: Long, arr: LongArray) {



            var d1=Date(min)
            var d2=Date(max)



        if(d1.minutes<10){

            currentView.min.text =""+d1.hours + ":0" +d1.minutes

        }else{

            currentView.min.text =""+d1.hours + ":" +d1.minutes


        }


        if(d2.minutes<10){

            currentView.max.text =""+d2.hours + ":0" +d2.minutes

        }else{

            currentView.max.text =""+d2.hours + ":" +d2.minutes


        }


        val series = ValueLineSeries()
        series.color = -0xa9480f

        var c=1;

        series.addPoint(ValueLinePoint(" ", 0.toFloat()))

        series.addPoint(ValueLinePoint("Day "+1, arr[6].toFloat()))
        series.addPoint(ValueLinePoint("Day "+2, arr[5].toFloat()))
        series.addPoint(ValueLinePoint("Day "+3, arr[4].toFloat()))
        series.addPoint(ValueLinePoint("Day "+4, arr[3].toFloat()))
        series.addPoint(ValueLinePoint("Day "+5, arr[2].toFloat()))
        series.addPoint(ValueLinePoint("Day "+6, arr[1].toFloat()))
        series.addPoint(ValueLinePoint("Day "+7, arr[0].toFloat()))

        series.addPoint(ValueLinePoint(" ", 0.toFloat()))




        var mCubicValueLineChart =currentView.cubiclinechart as ValueLineChart
        mCubicValueLineChart.addSeries(series)
        mCubicValueLineChart.startAnimation()


    }

    fun getMainPresenter() = presenter
}