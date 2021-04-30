package com.sngs.swayam.edu.ui.login.handler

import android.util.Log
import android.view.View
import com.sngs.swayam.edu.data.model.LoginUser
import com.sngs.swayam.edu.ui.login.viewmodel.LoginViewModel

class LoginHandler(var listener: LoginCallbackListener) : LoginListener{
    override fun login(view: View, vm:LoginViewModel, user: LoginUser) {
        Log.println(Log.INFO, "LoginHandler","Entered user info "+user)
        vm.validateUser(user)
        listener.refreshUI()
    }
}