package com.sngs.swayam.edu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject(
    var subject_id:String?="",
    var subject_name:String?="") :  Parcelable {
    override fun toString(): String {
        return "Subject(subject_id=$subject_id, subject_name=$subject_name)"
    }
}