package com.sngs.swayam.edu.data.repository

import com.sngs.swayam.edu.data.api.StudAttendanceApiInterface

class StudAttendanceRepository private constructor(){
    private object HOLDER {
        val INSTANCE = StudAttendanceRepository()
    }

    companion object {
        val instance: StudAttendanceRepository by lazy { HOLDER.INSTANCE }
    }

    private var apiInterface: StudAttendanceApiInterface? = null
}