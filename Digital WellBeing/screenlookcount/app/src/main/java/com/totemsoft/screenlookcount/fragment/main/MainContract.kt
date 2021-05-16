package com.totemsoft.screenlookcount.fragment.main

import android.content.Context
import com.totemsoft.screenlookcount.BaseMvpPresenter
import com.totemsoft.screenlookcount.BaseView

/**
 * View - Presenter contract for About fragment
 *
 * @author Antonina
 */
interface MainContract {
    interface Presenter : BaseMvpPresenter<MainContract.View> {
        fun showCalendarWithLooks(context: Context)
        fun showCalendarWithUnlocks(context: Context)
    }

    interface View : BaseView {
        fun setCountersView(looks: Int?, type: String?,min: Int,max: Int,arr: IntArray)
        fun setCountersView(looks: Int?, type: String?,min: Int,max: Int,arr: IntArray,arr_1:IntArray)
        fun setCountersView(looks: Int?, type: String?, min: Long, max: Long, arr: LongArray)


    }
}