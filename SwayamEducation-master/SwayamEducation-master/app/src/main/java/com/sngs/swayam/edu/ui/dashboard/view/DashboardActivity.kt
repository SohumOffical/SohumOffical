package com.sngs.swayam.edu.ui.dashboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.databinding.ActivityDashboardBinding
import com.sngs.swayam.edu.ui.dashboard.adapter.DashboardAdapter
import com.sngs.swayam.edu.ui.dashboard.viewmodel.DashboardViewModel
import com.sngs.swayam.edu.utils.common.Utilities
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var vm: DashboardViewModel
    private lateinit var adapter: DashboardAdapter
    private lateinit var activityDashboardBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
        vm = ViewModelProvider(this)[DashboardViewModel::class.java]

        initAdapter()

        vm.fetchDashbaordInfo()

        vm.dashboardListLiveData?.observe(this, Observer {
            Log.println(Log.INFO, "DashboardActivity","it "+it)
            if (it!=null){
                dashboard.visibility = View.VISIBLE

                adapter.setData(it as ArrayList<Dashboard>)
            }else{
                Utilities().showToast(this,"Error fetching dashboard information")
            }
            progress_dashboard.visibility = View.GONE
        })

    }

    private fun initAdapter() {
        activityDashboardBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(
                this@DashboardActivity, R.layout.activity_dashboard
        );

        val recyclerView: RecyclerView = activityDashboardBinding.dashboard
        adapter = DashboardAdapter()
        recyclerView.layoutManager = GridLayoutManager(this,3)
        recyclerView.adapter = adapter
    }
}