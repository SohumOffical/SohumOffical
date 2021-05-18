package com.sohumngs.uapps.dw.fragment.about

import android.content.Context
import com.sohumngs.uapps.dw.BaseMvpPresenter
import com.sohumngs.uapps.dw.BaseView

/**
 * View - Presenter contract for About fragment
 *
 * @author Antonina
 */
interface AboutContract {
    interface Presenter : BaseMvpPresenter<View> {
        fun contactMe(context: Context)
    }

    interface View : BaseView
}