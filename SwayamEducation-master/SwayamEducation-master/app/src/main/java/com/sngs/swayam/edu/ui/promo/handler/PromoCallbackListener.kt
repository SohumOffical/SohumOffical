package com.sngs.swayam.edu.ui.promo.handler

import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.utils.common.PromoOps

interface PromoCallbackListener {
    fun setDashboard(dashboard: Dashboard)
    fun refreshUI(promoOps: PromoOps, position:Int?=0, promo:Promo?=null)
    fun backPressed()
}