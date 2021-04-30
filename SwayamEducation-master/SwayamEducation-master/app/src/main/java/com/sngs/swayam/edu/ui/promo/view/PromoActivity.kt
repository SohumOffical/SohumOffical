package com.sngs.swayam.edu.ui.promo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.databinding.ActivityPromoBinding
import com.sngs.swayam.edu.ui.dashboard.handler.DashboardHandler
import com.sngs.swayam.edu.ui.promo.handler.PromoHandler
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel

class PromoActivity : AppCompatActivity() {
    private lateinit var vm: PromoViewModel
    private lateinit var activityPromodBinding: ActivityPromoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vm = ViewModelProvider(this)[PromoViewModel::class.java]

        init()
    }

    private fun init() {
        val intent = intent

        activityPromodBinding = DataBindingUtil.setContentView<ActivityPromoBinding>(
            this@PromoActivity, R.layout.activity_promo
        );
        activityPromodBinding.dashboard = intent.getParcelableExtra("dashboard")!!
        if(activityPromodBinding.handler==null)
            activityPromodBinding.handler = PromoHandler()

    }
}