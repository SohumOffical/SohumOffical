package com.sngs.swayam.edu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dashboard(
        var title:String?="",
        var isVisible:Boolean?=false,
        var canCreate:Boolean?=false,
        var canUpdate:Boolean?=false,
        var canSearch:Boolean?=false,
        var canDelete:Boolean?=false
) : Parcelable {
        override fun toString(): String {
                return "Dashboard(title=$title, isVisible=$isVisible, canCreate=$canCreate, canUpdate=$canUpdate, canSearch=$canSearch, canDelete=$canDelete)"
        }
}