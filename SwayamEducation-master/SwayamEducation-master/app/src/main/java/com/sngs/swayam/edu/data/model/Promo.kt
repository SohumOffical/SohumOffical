package com.sngs.swayam.edu.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Promo (
    var id:Int?=0,
    var owner:String?="",
    var title:String?="",
    var description:String?="",
    var price: Int?=0

) : Parcelable {
    override fun toString(): String {
        return "Promo(id=$id, ownername=$owner, title=$title, descp=$description, price=$price)"
    }
}