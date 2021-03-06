package com.sohumngs.uapps.dw

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohumngs.uapps.R
import com.sohumngs.uapps.dw.background.LookCounterService
import com.sohumngs.uapps.dw.fragment.main.AverageUseFragment
import com.sohumngs.uapps.dw.utils.*
import kotlinx.android.synthetic.main.activity_main.sv_container
import kotlinx.android.synthetic.main.activity_pickups.*


class AverageUse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_average_use)


        back.setOnClickListener{

            finish()

        }

    }

    override fun onStart() {
        super.onStart()

        if (AppPreferences.shouldRunCountingService) {
            startService()
        }

        if (!isFragmentInBackStack(C.FRAGMENT_TAG_MAIN)) {
            addFragment(AverageUseFragment::class.java, C.FRAGMENT_TAG_MAIN, sv_container, false, null)
        }
    }


    private fun startService() {
        startService(Intent(this, LookCounterService::class.java))
    }

}