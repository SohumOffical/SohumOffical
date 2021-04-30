package com.sngs.swayam.edu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standard(
        var standard_id:String?="",
        var standard_name:String?="") : Parcelable {
    override fun toString(): String {
        return "Standard(standard_id=$standard_id, standard_name=$standard_name)"
    }
}