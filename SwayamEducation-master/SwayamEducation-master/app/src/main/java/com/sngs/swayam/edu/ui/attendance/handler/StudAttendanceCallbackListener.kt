package com.sngs.swayam.edu.ui.attendance.handler

import com.sngs.swayam.edu.data.model.Standard

interface StudAttendanceCallbackListener {
    fun backPressed()
    fun standardPressed(standard : Standard)
    fun datePicker(type : Int)
}