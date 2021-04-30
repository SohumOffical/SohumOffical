package com.sngs.swayam.edu.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.LoginUser
import com.sngs.swayam.edu.databinding.ActivityLoginBinding
import com.sngs.swayam.edu.ui.dashboard.view.DashboardActivity
import com.sngs.swayam.edu.ui.login.handler.LoginCallbackListener
import com.sngs.swayam.edu.ui.login.handler.LoginHandler
import com.sngs.swayam.edu.ui.login.viewmodel.LoginViewModel
import com.sngs.swayam.edu.utils.common.Utilities

class LoginActivity : AppCompatActivity(), LoginCallbackListener {
    private lateinit var vm: LoginViewModel
    private lateinit var activityLoginBinding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        vm = ViewModelProvider(this)[LoginViewModel::class.java]

        init()
    }

    fun init(){
        activityLoginBinding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this, R.layout.activity_login
        )

        activityLoginBinding.user = LoginUser()
        activityLoginBinding.viewmodel = vm
        activityLoginBinding.handlers = LoginHandler(this)
    }


    override fun refreshUI(){
        vm.userValidateLiveData?.observe(this@LoginActivity, Observer {
            Log.println(Log.INFO, "LoginActivity","Is user valid "+it.toString())
            if(it) {
                Log.println(Log.INFO, "LoginActivity","Moving to dashboard page ")
                val i = Intent(this@LoginActivity, DashboardActivity::class.java)
                startActivity(i)
            }else{
                Utilities().showToast(this,"Email or Password are incorrect")
            }
        })
    }
}