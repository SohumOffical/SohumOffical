package com.sngs.swayam.edu.ui.dashboard.handler

import android.content.Intent
import android.util.Log
import android.view.View
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.ui.attendance.view.StudentAttendanceActivity
import com.sngs.swayam.edu.ui.dashboard.view.DashboardActivity
import com.sngs.swayam.edu.ui.promo.view.PromoActivity
import com.sngs.swayam.edu.ui.student.view.StudentActivity
import com.sngs.swayam.edu.utils.common.DashboardCards

class DashboardHandler() : DashboardListener {
    override fun onClick(view: View, dashboard: Dashboard) {
        Log.println(Log.INFO, "DashboardHandler","onClick cliked on "+dashboard)
        var intent:Intent?=null
        when(dashboard.title){
           // DashboardCards.CLASS.title -> i = Intent(view.context, PromoActivity::class.java)
           // DashboardCards.FEES.title -> i = Intent(view.context, PromoActivity::class.java)
            DashboardCards.ATTENDANCE.title -> intent = Intent(view.context, StudentAttendanceActivity::class.java)
         //   DashboardCards.SUBJECTS.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.STAFF.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.USERLIST.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.HOMEWORK.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.EXAMRESULT.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.MSG.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.TRACKLOCATION.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.GALLERY.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.TIMETABLE.title -> i = Intent(view.context, PromoActivity::class.java)
         //   DashboardCards.HOLIDAY.title -> i = Intent(view.context, PromoActivity::class.java)
            DashboardCards.STUDENT.title -> intent = Intent(view.context, StudentActivity::class.java)
        }
        if(intent!=null){
            intent?.putExtra("dashboard", dashboard)
            view.context.startActivity(intent)
        }
    }
}