package com.sohumngs.uapps.dw.fragment.calendar

import android.content.Context
import com.sohumngs.uapps.dw.BaseMvpPresenter
import com.sohumngs.uapps.dw.BaseView
import com.sohumngs.uapps.dw.utils.ScreenLookCategory
import java.util.*

/**
 * View - Presenter contract for Calendar fragment
 *
 * @author Antonina
 */
interface CalendarContract {
    interface Presenter : BaseMvpPresenter<View> {
        fun populateList(context: Context?, date: Calendar, screenLookCategory: ScreenLookCategory)
    }

    interface View : BaseView {
        fun setViewTitle(titleMonth: Calendar)

        fun setWeekLabels(weekLabels: List<String>)

        fun setDatesToCalendar(dates: List<Pair<Calendar, Int?>>)
    }
}