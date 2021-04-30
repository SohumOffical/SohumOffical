package com.sngs.swayam.edu.ui.attendance.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.data.model.Standard
import com.sngs.swayam.edu.data.model.Student
import com.sngs.swayam.edu.data.model.Subject
import com.sngs.swayam.edu.databinding.ActivityStudentAttendanceBinding
import com.sngs.swayam.edu.ui.attendance.handler.StudAttendanceCallbackListener
import com.sngs.swayam.edu.ui.attendance.viewmodel.AttendanceViewModel
import com.sngs.swayam.edu.ui.standard.adapter.StandardAdapter
import com.sngs.swayam.edu.ui.student.adapter.StudentAdapter
import com.sngs.swayam.edu.utils.common.Utilities
import kotlinx.android.synthetic.main.activity_view_promos.*

class StudentAttendanceActivity : AppCompatActivity() , StudAttendanceCallbackListener {
    private lateinit var vm: AttendanceViewModel
    private lateinit var activityStudentAttendanceBinding: ActivityStudentAttendanceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
        );
        init()
    }

    private fun init() {
        activityStudentAttendanceBinding = DataBindingUtil.setContentView<ActivityStudentAttendanceBinding>(
            this, R.layout.activity_student_attendance
        )
        vm = ViewModelProvider(this)[AttendanceViewModel::class.java]

        vm.fetchAllStandard()
        vm.standardListLiveData?.observe(this, Observer {
            Log.println(Log.INFO, "ViewPromosActivity","it "+it)
            if (it!=null){
              var  standard_adapter = StandardAdapter(this, R.layout.spinnerlayout, it as ArrayList<Standard>)
              activityStudentAttendanceBinding.selectStandardSp.adapter = standard_adapter
            }
            else{
                Utilities().showToast(this,"Error fetching promos list")
            }
        })



        vm.fetchAllStudent()
        vm.studentListLiveData?.observe(this, Observer {
            Log.println(Log.INFO, "ViewPromosActivity","it "+it)
            if (it!=null){
                var  student_adapter = StudentAdapter(this, R.layout.spinnerlayout, it as ArrayList<Student>)
                activityStudentAttendanceBinding.selectStudentSp.adapter = student_adapter
            }
            else{
                Utilities().showToast(this,"Error fetching promos list")
            }
        })
    }

    override fun backPressed() {
        finish()
    }

    override fun standardPressed(standard: Standard) {
        Log.println(Log.INFO, "StandardHandler","onStandard cliked on "+" "
                +standard.standard_id+" "+standard.standard_name)
    }

    override fun datePicker(type: Int) {
        //activityStudentAttendanceBinding.d
    }


}