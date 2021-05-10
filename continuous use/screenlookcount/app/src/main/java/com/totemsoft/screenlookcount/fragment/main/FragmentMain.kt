package com.totemsoft.screenlookcount.fragment.main

import android.os.Build
import android.os.Bundle
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


/**
 * Fragment which contains screen view and screen unlock data.
 *
 * @author Antonina
 */
class FragmentMain : BaseFragment(), MainContract.View{

    private lateinit var currentView: View
    private lateinit var presenter: MainPresenter


    override fun getContentResource() = R.layout.fragment_main

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
            presenter.setCountersValues(it, nowDateString, "total")

            presenter.setCountersValues(it, nowDateString, "today")

        }

    }

    override fun setCountersView(looks: Int?, type: String?, min: Int, max: Int, arr: IntArray) {


        if(type.equals("total")){

            currentView.total.text = (looks ?: 0).toString()


            currentView.min.text =min.toString()

            currentView.max.text =max.toString()


        }else if(type.equals("today")){

                try {
                    currentView.dailyaverage.text = (min / max).toString()
                }catch (e:Exception){
                    currentView.dailyaverage.text = ""

                }

        }



        val series = ValueLineSeries()
        series.color = -0xa9480f

        var c=1;

        series.addPoint(ValueLinePoint("Day "+1, arr[6].toFloat()))
        series.addPoint(ValueLinePoint("Day "+2, arr[5].toFloat()))
        series.addPoint(ValueLinePoint("Day "+3, arr[4].toFloat()))
        series.addPoint(ValueLinePoint("Day "+4, arr[3].toFloat()))
        series.addPoint(ValueLinePoint("Day "+5, arr[2].toFloat()))
        series.addPoint(ValueLinePoint("Day "+6, arr[1].toFloat()))
        series.addPoint(ValueLinePoint("Day "+7, arr[0].toFloat()))



        var mCubicValueLineChart =currentView.cubiclinechart as ValueLineChart
        mCubicValueLineChart.addSeries(series)
        mCubicValueLineChart.startAnimation()


    }

    fun getMainPresenter() = presenter
}