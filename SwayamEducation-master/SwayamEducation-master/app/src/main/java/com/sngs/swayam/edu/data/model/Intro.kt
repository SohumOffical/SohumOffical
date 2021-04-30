package com.sngs.swayam.edu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Intro(var img:Int?=0,
                 var header_text:String?=""
) : Parcelable {
    override fun toString(): String {
        return "Intro(img=$img, header_text=$header_text)"
    }
}

