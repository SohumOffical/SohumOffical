package com.sngs.swayam.edu.ui.student.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sngs.swayam.edu.R

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_student)
    }
}