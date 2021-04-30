package com.sngs.swayam.edu.ui.dashboard.handler

import android.view.View
import com.sngs.swayam.edu.data.model.Dashboard

interface DashboardListener{
    fun onClick(view: View, dashboard: Dashboard)
}