package com.totemsoft.screenlookcount

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.totemsoft.screenlookcount.background.LookCounterService
import com.totemsoft.screenlookcount.fragment.main.LastDropOffFragment
import com.totemsoft.screenlookcount.utils.*
import kotlinx.android.synthetic.main.activity_main.back
import kotlinx.android.synthetic.main.activity_main.sv_container


class LastDropOff : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_drop_off)

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
            addFragment(LastDropOffFragment::class.java, C.FRAGMENT_TAG_MAIN, sv_container, false, null)
        }
    }


    private fun startService() {
        startService(Intent(this, LookCounterService::class.java))
    }

}