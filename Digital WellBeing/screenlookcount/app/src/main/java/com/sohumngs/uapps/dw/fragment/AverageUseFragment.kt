package com.sohumngs.uapps.dw.fragment.main

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



class AverageUseFragment : BaseFragment(), MainContract.View{

    private lateinit var currentView: View
    private lateinit var presenter: MainPresenter
    val series = ValueLineSeries()


    override fun getContentResource() = R.layout.fragment_average_use

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
            presenter.setCountersValues(it, nowDateString, "averageuse")


        }

    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray) {


        var p1: Int = looks?.rem(60) ?: 0
        var p2: Int = looks?.div(60) ?: 0
        var p3 = p2 % 60
        p2 = p2 / 60

        if(p3==0&&p2==0){
            currentView.total.text = ""+p1+"s"
        }else if(p2==0){

            currentView.total.text = ""+p3+"min "+p1+"s"

        }else if(p3==0){
            currentView.total.text = ""+p2+"hr"
        }

        else{

            currentView.total.text = ""+p2+"hr "+""+p3+"min "+p1+"s"

        }



        if (looks != null) {

           var looks_1=looks/7;



            val p1: Int = looks_1?.rem(60) ?: 0
            var p2: Int = looks_1?.div(60) ?: 0
            val p3 = p2 % 60
            p2 = p2 / 60

            if(p3==0&&p2==0){
                currentView.dailyaverage.text = ""+p1+"s"
            }else if(p2==0){

                currentView.dailyaverage.text = ""+p3+"min "+p1+"s"

            }else if(p3==0){
                currentView.dailyaverage.text = ""+p2+"hr"
            }

            else{

                currentView.dailyaverage.text = ""+p2+"hr "+""+p3+"min "+p1+"s"

            }


        }



        p1 = min?.rem(60) ?: 0
        p2 = min?.div(60) ?: 0
        p3 = p2 % 60
        p2 = p2 / 60

        if(p3==0&&p2==0){
            currentView.min.text = ""+p1+"s"
        }else if(p2==0){

            currentView.min.text = ""+p3+"min "+p1+"s"

        }else if(p3==0){
            currentView.min.text = ""+p2+"hr"
        }

        else{

            currentView.min.text = ""+p2+"hr "+""+p3+"min "+p1+"s"

        }

        p1 = max?.rem(60) ?: 0
        p2 = max?.div(60) ?: 0
        p3 = p2 % 60
        p2 = p2 / 60

        if(p3==0&&p2==0){
            currentView.max.text = ""+p1+"s"
        }else if(p2==0){

            currentView.max.text = ""+p3+"min "+p1+"s"

        }else if(p3==0){
            currentView.max.text = ""+p2+"hr"
        }

        else{

            currentView.max.text = ""+p2+"hr "+""+p3+"min "+p1+"s"

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
        series.addPoint(ValueLinePoint("Today", arr[0].toFloat()))


        series.addPoint(ValueLinePoint(" ", 0.toFloat()))




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