package com.sohumngs.uapps.dw.fragment.main

import android.content.Context
import com.sohumngs.uapps.dw.BaseMvpPresenter
import com.sohumngs.uapps.dw.BaseView

/**
 * View - Presenter contract for About fragment
 *
 * @author Antonina
 */
interface MainContract {
    interface Presenter : BaseMvpPresenter<View> {
        fun showCalendarWithLooks(context: Context)
        fun showCalendarWithUnlocks(context: Context)
    }

    interface View : BaseView {
        fun setCountersView(looks: Int?, type: String?,min: Int,max: Int,arr: IntArray)
        fun setCountersView(looks: Int?, type: String?,min: Int,max: Int,arr: IntArray,arr_1:IntArray)
        fun setCountersView(looks: Int?, type: String?, min: Long, max: Long, arr: LongArray)


    }
}