package com.sngs.swayam.edu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    var id:String?="",
    var name:String?="",
    var standard_id:String?="",
    var standard_name:String?="",
    var emergencyContact:String?="") : Parcelable {
    override fun toString(): String {
        return "Student(id=$id, name=$name,standard_id=$standard_id,standard_name=$standard_name," +
                "emergencyContact=$emergencyContact)"
    }
}