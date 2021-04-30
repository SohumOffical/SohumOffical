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
import com.sngs.swayam.edu.databinding.ActivityCreatePromoBinding
import com.sngs.swayam.edu.ui.promo.adapter.PromoAdapter
import com.sngs.swayam.edu.ui.promo.handler.PromoCallbackListener
import com.sngs.swayam.edu.ui.promo.handler.PromoHandler
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel
import com.sngs.swayam.edu.utils.common.PromoOps
import com.sngs.swayam.edu.utils.common.Utilities

class CreatePromoActivity : AppCompatActivity(), PromoCallbackListener {
    private lateinit var vm: PromoViewModel
    private lateinit var activityCreatePromoBinding: ActivityCreatePromoBinding
    private lateinit var adapter: PromoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vm = ViewModelProvider(this)[PromoViewModel::class.java]

        init()
    }

    private fun init() {
        val intent = intent
        activityCreatePromoBinding = DataBindingUtil.setContentView<ActivityCreatePromoBinding>(
            this@CreatePromoActivity, R.layout.activity_create_promo
        );
        activityCreatePromoBinding.promo = Promo()
        activityCreatePromoBinding.dashboard = intent.getParcelableExtra("dashboard")!!
        activityCreatePromoBinding.viewmodel=vm
        if(activityCreatePromoBinding.handler==null)
            activityCreatePromoBinding.handler = PromoHandler(this)
        adapter = PromoAdapter(this, vm, intent)
    }

    override fun setDashboard(dashboard: Dashboard) {

    }

    override fun refreshUI(promoOps: PromoOps, position: Int?, promo: Promo?){
        Log.println(Log.INFO, "CreatePromoActivity","refreshUI exec")
        vm.createPromoLiveData?.observe(this@CreatePromoActivity, Observer {

            if (it!=null){
                Log.println(Log.INFO, "CreatePromoActivity","Promo created")
                //TO to update adapter to add new item
                adapter.notifyDataSetChanged() //for local config
                //adapter.addData(it)
                val i = Intent(this@CreatePromoActivity, ViewPromosActivity::class.java)
                i?.putExtra("dashboard",activityCreatePromoBinding.dashboard)
                startActivity(i)
                finish()
            }else{
                Utilities().showToast(this,"Cannot create promo at the moment")
            }
        })
        //This is not properly updating the ui and its calling ViewPromosActivity before addData completes
//        Utilities().withDelay(10) {
//            val i = Intent(this@CreatePromoActivity, ViewPromosActivity::class.java)
//            i?.putExtra("dashboard",activityCreatePromoBinding.dashboard)
//            startActivity(i)
//            finish()
//        }
    }

    override fun backPressed() {
        finish()
    }

}