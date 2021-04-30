package com.sngs.swayam.edu.ui.promo.handler

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.ui.promo.view.CreatePromoActivity
import com.sngs.swayam.edu.ui.promo.view.ViewPromosActivity
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel
import com.sngs.swayam.edu.utils.common.PromoOps

class PromoHandler(var listener: PromoCallbackListener?=null, var position:Int?=0) : PromoListener {

    fun onBackClick(view: View){
        Log.println(Log.INFO, "PromoHandler","onBackevent cliked on ")
        listener?.backPressed()
    }

    override fun onCreatePromoClick(view: View, dashboard: Dashboard) {
        var i: Intent = Intent(view.context, CreatePromoActivity::class.java)
        i?.putExtra("dashboard", dashboard)
        view.context.startActivity(i)
    }

    override fun onViewPromosClick(view: View, dashboard: Dashboard){
        var i: Intent = Intent(view.context, ViewPromosActivity::class.java)
        i?.putExtra("dashboard", dashboard)
        view.context.startActivity(i)
    }

    override fun onCreateClick(view: View, vm: PromoViewModel, promo: Promo){
        Log.println(Log.INFO, "PromoHandler","onCreateClick cliked on "+promo)
        vm.createPromo(promo)
        listener?.refreshUI(PromoOps.CREATE,0)
    }

    override fun onEditClick(view: View, vm: PromoViewModel, promo: Promo, dashboard: Dashboard) {
        Log.println(Log.INFO, "PromoHandler", "onEditClick cliked on " + promo)
        listener?.setDashboard(dashboard)
        listener?.refreshUI(PromoOps.EDIT, position!!)
    }

    override fun onDeleteClick(view: View, vm: PromoViewModel, promo: Promo) {
        Log.println(Log.INFO, "PromoHandler", "onDeleteClick cliked on " + promo)
        vm.deletePromo(promo.id!!)
        listener?.refreshUI(PromoOps.DELETE, position!!,promo)
    }

    override fun onUpdateClick(view: View, vm: PromoViewModel, promo: Promo) {
        Log.println(Log.INFO, "PromoHandler", "onUpdateClick cliked on " + promo)
        vm.updatePromo(promo)
        listener?.refreshUI(PromoOps.UPDATE, position!!, promo)
    }
}