package com.sngs.swayam.edu.ui.attendance.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Subject
import com.sngs.swayam.edu.databinding.ActivityStudentAttendanceBinding
import com.sngs.swayam.edu.ui.attendance.handler.StudAttendanceCallbackListener
import com.sngs.swayam.edu.ui.attendance.viewmodel.AttendanceViewModel

class AttendanceSubjectListItemAdapter(var listener: StudAttendanceCallbackListener, var vm: AttendanceViewModel, var intent: Intent) :
    RecyclerView.Adapter<AttendanceSubjectListItemAdapter.AttendanceSubjectViewHolder>() {

    private var data: ArrayList<Subject>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttendanceSubjectListItemAdapter.AttendanceSubjectViewHolder {
        val activityStudentAttendanceBinding: ActivityStudentAttendanceBinding =
            DataBindingUtil.inflate<ActivityStudentAttendanceBinding>(
                LayoutInflater.from(parent.context), R.layout.activity_promo_list, parent, false)

        val holder = AttendanceSubjectListItemAdapter.AttendanceSubjectViewHolder(activityStudentAttendanceBinding)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: AttendanceSubjectListItemAdapter.AttendanceSubjectViewHolder, position: Int) {

    }

    class AttendanceSubjectViewHolder(activityStudentAttendanceBinding: ActivityStudentAttendanceBinding) :
        RecyclerView.ViewHolder(activityStudentAttendanceBinding.getRoot()) {
        val AttendSubListBinding: ActivityStudentAttendanceBinding
        init {
            AttendSubListBinding = activityStudentAttendanceBinding
        }
    }
}