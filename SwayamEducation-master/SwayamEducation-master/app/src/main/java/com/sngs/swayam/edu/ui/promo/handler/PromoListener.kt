package com.sngs.swayam.edu.ui.promo.handler

import android.view.View
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel

interface PromoListener{
    fun onCreatePromoClick(view: View, dashboard: Dashboard)
    fun onViewPromosClick(view: View, dashboard: Dashboard)

    fun onCreateClick(view: View, vm: PromoViewModel, promo: Promo)
    fun onEditClick(view: View, vm: PromoViewModel, promo: Promo, dashboard: Dashboard)
    fun onDeleteClick(view: View, vm: PromoViewModel, promo: Promo)
    fun onUpdateClick(view: View, vm: PromoViewModel, promo: Promo)
}