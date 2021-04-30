package com.sngs.swayam.edu.ui.attendance.handler

import android.text.style.TabStopSpan
import android.util.Log
import android.view.View
import com.sngs.swayam.edu.data.model.Standard
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel


class StudentHandler (var listener: StudAttendanceCallbackListener?=null) : StudentListener {

    fun onBackClick(view: View){
        Log.println(Log.INFO, "StudentHandler","onBackevent cliked on ")
        listener?.backPressed()
    }

    fun onStandardClick(view: View, standard: Standard){
        Log.println(Log.INFO, "StandardHandler","onStandard cliked on ")
        listener?.standardPressed(standard)
    }

    fun onDatepicker(view: View, type: Int){
        Log.println(Log.INFO, "StandardHandler","onStandard cliked on ")
        listener?.datePicker(type)
    }


}