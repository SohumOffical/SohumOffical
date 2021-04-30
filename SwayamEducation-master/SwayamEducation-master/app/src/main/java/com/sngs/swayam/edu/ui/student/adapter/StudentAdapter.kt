package com.sngs.swayam.edu.ui.student.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.sngs.swayam.edu.data.model.Standard
import com.sngs.swayam.edu.data.model.Student

class StudentAdapter (context: Context, @LayoutRes private val layoutResource: Int, private val student: List<Student>): ArrayAdapter<Student>(context, layoutResource, student) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TextView
        view.text = student[position].standard_name +student[position].emergencyContact
        return view
    }
}