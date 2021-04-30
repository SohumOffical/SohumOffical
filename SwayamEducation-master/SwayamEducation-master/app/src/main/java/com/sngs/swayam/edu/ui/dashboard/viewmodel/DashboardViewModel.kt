package com.sngs.swayam.edu.ui.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.repository.DashboardRepository

class DashboardViewModel(application: Application): AndroidViewModel(application) {
    private var dashboardRepository: DashboardRepository? = null
    var dashboardListLiveData : LiveData<List<Dashboard>>?=null

    init {
        dashboardRepository = DashboardRepository()
        dashboardListLiveData = MutableLiveData()
    }

    fun fetchDashbaordInfo(){
        dashboardListLiveData = dashboardRepository?.fetchDashboardInfo();
    }
}