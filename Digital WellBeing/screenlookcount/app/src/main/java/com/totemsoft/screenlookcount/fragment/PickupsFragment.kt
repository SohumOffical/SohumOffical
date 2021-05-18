package com.totemsoft.screenlookcount.fragment.main

import android.os.Build
import android.os.Bundle
import android.util.Log
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



class PickupsFragment : BaseFragment(), MainContract.View{

    private lateinit var currentView: View
    private lateinit var presenter: MainPresenter
    val series = ValueLineSeries()
    var c=0;



    override fun getContentResource() = R.layout.fragment_pickups

    override fun init(view: View, state: Bundle?) {
        currentView = view
        presenter = MainPresenter()
        presenter.attach(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        context?.let {
            val nowDateString = Date();
            presenter.setCountersValues(it, nowDateString, "pickups")

        }

    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray) {


        currentView.total.text = (looks ?: 0).toString()

        if (looks != null) {
            currentView.dailyaverage.text =(looks/7).toString()
        }

        currentView.min.text =min.toString()

        currentView.max.text =max.toString()



        series.color = -0xa9480f

        c++;

        if(c==7){

            series.addPoint(ValueLinePoint("Day " + 1, arr[6].toFloat()))
            series.addPoint(ValueLinePoint("Day " + 2, arr[5].toFloat()))
            series.addPoint(ValueLinePoint("Day " + 3, arr[4].toFloat()))
            series.addPoint(ValueLinePoint("Day " + 4, arr[3].toFloat()))
            series.addPoint(ValueLinePoint("Day " + 5, arr[2].toFloat()))
            series.addPoint(ValueLinePoint("Day " + 6, arr[1].toFloat()))
            series.addPoint(ValueLinePoint("Day " + 7, arr[0].toFloat()))



            Log.d("ValueLinePoint",arr[0].toFloat().toString());
            Log.d("ValueLinePoint",arr[1].toFloat().toString());
            Log.d("ValueLinePoint",arr[2].toFloat().toString());
            Log.d("ValueLinePoint",arr[3].toFloat().toString());
            Log.d("ValueLinePoint",arr[4].toFloat().toString());
            Log.d("ValueLinePoint",arr[5].toFloat().toString());
            Log.d("ValueLinePoint",arr[6].toFloat().toString());


        }







        var mCubicValueLineChart =currentView.cubiclinechart as ValueLineChart
        mCubicValueLineChart.addSeries(series)
        mCubicValueLineChart.startAnimation()


    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray, arr_1: IntArray) {
        TODO("Not yet implemented")
    }

    override fun setCountersView(looks: Int?, type: String?, min: Long, max: Long, arr: LongArray) {
        TODO("Not yet implemented")
    }

    fun getMainPresenter() = presenter
}