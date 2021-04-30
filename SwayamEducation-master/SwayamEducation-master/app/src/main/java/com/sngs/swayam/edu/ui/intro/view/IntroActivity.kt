package com.sngs.swayam.edu.ui.intro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Intro
import com.sngs.swayam.edu.databinding.ActivityIntroBinding
import com.sngs.swayam.edu.ui.dashboard.viewmodel.DashboardViewModel
import com.sngs.swayam.edu.ui.intro.adapter.IntroAdapter
import com.sngs.swayam.edu.ui.intro.handler.IntroCallbackListener
import com.sngs.swayam.edu.ui.intro.handler.IntroHandler
import com.sngs.swayam.edu.ui.intro.viewmodel.IntroViewModel
import com.sngs.swayam.edu.ui.login.LoginActivity
import com.sngs.swayam.edu.utils.common.Utilities
import kotlinx.android.synthetic.main.activity_dashboard.*

class IntroActivity : AppCompatActivity(), IntroCallbackListener
{
    private lateinit var vm: IntroViewModel
    private lateinit var activityIntroBinding: ActivityIntroBinding
    private var adapter: IntroAdapter = IntroAdapter()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vm = ViewModelProvider(this)[IntroViewModel::class.java]
        vm.fetchIntroInfo()

        init()
    }

    private fun init() {
        activityIntroBinding = DataBindingUtil.setContentView<ActivityIntroBinding>(
            this, R.layout.activity_intro
        )
        activityIntroBinding.handlers = IntroHandler(this)
        activityIntroBinding.introViewpager.adapter = adapter


        vm.introListLiveData?.observe(this, Observer {
            Log.println(Log.INFO, "IntroActivity","it "+it)
            if (it!=null){
                adapter.setData(it as ArrayList<Intro>)
                activityIntroBinding.dots.setViewPager(activityIntroBinding.introViewpager)
            }else{
                Utilities().showToast(this,"Error fetching intro information")
            }
        })
    }

    override fun moveLoginPage()
    {
        Log.println(Log.INFO, "IntroActivity","Moving to Login page")
        val i = Intent(this@IntroActivity, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}