package com.sngs.swayam.edu.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.databinding.ActivityDashboardListBinding
import com.sngs.swayam.edu.ui.dashboard.handler.DashboardHandler


class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private var data: ArrayList<Dashboard>? = null

    fun setData(list: ArrayList<Dashboard>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val activityDashboardListBinding:ActivityDashboardListBinding =
            DataBindingUtil.inflate<ActivityDashboardListBinding>(
            LayoutInflater.from(parent.context), R.layout.activity_dashboard_list, parent, false)

        val holder = DashboardViewHolder(activityDashboardListBinding)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item = data?.get(position)
        holder.dashboardListBinding.dashboard = item!!
        if(holder.dashboardListBinding.handler==null)
            holder.dashboardListBinding.handler = DashboardHandler()
    }

    class DashboardViewHolder(activityDashboardListBinding: ActivityDashboardListBinding) :
            RecyclerView.ViewHolder(activityDashboardListBinding.getRoot()) {
        val dashboardListBinding: ActivityDashboardListBinding

        init {
            dashboardListBinding = activityDashboardListBinding
        }
    }
}