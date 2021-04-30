package com.sngs.swayam.edu.ui.promo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.databinding.ActivityPromoEditBinding
import com.sngs.swayam.edu.ui.promo.adapter.PromoAdapter
import com.sngs.swayam.edu.ui.promo.handler.PromoCallbackListener
import com.sngs.swayam.edu.ui.promo.handler.PromoHandler
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel
import com.sngs.swayam.edu.utils.common.PromoOps
import com.sngs.swayam.edu.utils.common.Utilities

class PromoEditActivity : AppCompatActivity(), PromoCallbackListener {
    private lateinit var vm: PromoViewModel
    private lateinit var activityPromoEditBinding: ActivityPromoEditBinding
    private lateinit var adapter: PromoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vm = ViewModelProvider(this)[PromoViewModel::class.java]

        init()
    }

    private fun init() {
        val intent = intent
        activityPromoEditBinding = DataBindingUtil.setContentView<ActivityPromoEditBinding>(
                this@PromoEditActivity, R.layout.activity_promo_edit
        );
        activityPromoEditBinding.dashboard = intent.getParcelableExtra("dashboard")!!
        activityPromoEditBinding.promo = intent.getParcelableExtra("promo")!!
        activityPromoEditBinding.position = intent.getStringExtra("position")!!.toInt()
        activityPromoEditBinding.viewmodel=vm
        if(activityPromoEditBinding.handler==null)
            activityPromoEditBinding.handler = PromoHandler(this)
        adapter = PromoAdapter(this, vm, intent)
    }

    override fun setDashboard(dashboard: Dashboard) {

    }

    override fun refreshUI(promoOps: PromoOps, position: Int?, promo: Promo?) {
        Log.println(Log.INFO, "PromoEditActivity","refreshUI position "+position+" "+promo)
        vm.updatePromoLiveData?.observe(this, Observer {
            if (it!=null){
                adapter.notifyDataSetChanged() //for local config
                //adapter.updateData(position!!, promo!!)
            }else{
                Utilities().showToast(this,"Cannot update Promo at the moment!")
            }
        })

        //This is not properly updating the ui and its calling ViewPromosActivity before addData completes
        Utilities().withDelay(200) {
          /*  val i = Intent(this@PromoEditActivity, ViewPromosActivity::class.java)
            i?.putExtra("dashboard",activityPromoEditBinding.dashboard)
            startActivity(i)*/
            finish()
        }
    }

    override fun backPressed() {

    }
}