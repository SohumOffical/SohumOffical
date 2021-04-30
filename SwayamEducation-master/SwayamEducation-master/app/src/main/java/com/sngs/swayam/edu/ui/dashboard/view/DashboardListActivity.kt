package com.sngs.swayam.edu.ui.dashboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.databinding.ActivityDashboardListBinding

class DashboardListActivity : AppCompatActivity() {
    private lateinit var activityDashboardBinding: ActivityDashboardListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboardBinding = DataBindingUtil.setContentView<ActivityDashboardListBinding>(
            this@DashboardListActivity, R.layout.activity_dashboard_list
        )
    }

}