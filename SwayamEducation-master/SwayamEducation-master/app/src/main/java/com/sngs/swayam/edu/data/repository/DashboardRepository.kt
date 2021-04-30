package com.sngs.swayam.edu.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.Dashboard

class DashboardRepository {
    fun fetchDashboardInfo():LiveData<List<Dashboard>>{
        val data = MutableLiveData<List<Dashboard>>()
        val myList= ArrayList<Dashboard>()
        myList.add(Dashboard("Create Class", true, true, true, true, true))
        myList.add(Dashboard("Manage Fees", true,true, true, true, true))
        myList.add(Dashboard("Attendance", true,true, true, true, true))
        myList.add(Dashboard("Create Subjects", true,true, true, true, true))
        myList.add(Dashboard("Manage Staff", true,true, true, true, true))
        myList.add(Dashboard("User List", true,true, true, true, true))
        myList.add(Dashboard("Homework Entry", true,true, true, true, true))
        myList.add(Dashboard("Exam Result", true,true, true, true, true))
        myList.add(Dashboard("Message", true,true, true, true, true))
        myList.add(Dashboard("Track Location", true,true, true, true, true))
        myList.add(Dashboard("Gallery", true,true, true, true, true))
        myList.add(Dashboard("Time Table", true,true, true, true, true))
        myList.add(Dashboard("Holiday", true,true, true, true, true))
        myList.add(Dashboard("Manage Student", true,true, true, true, true))
        data.value = myList
        return data
    }
}