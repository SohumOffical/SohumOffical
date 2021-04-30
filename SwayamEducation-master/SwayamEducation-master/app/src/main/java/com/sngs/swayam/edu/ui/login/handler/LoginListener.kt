package com.sngs.swayam.edu.ui.login.handler

import android.view.View
import com.sngs.swayam.edu.data.model.LoginUser
import com.sngs.swayam.edu.ui.login.viewmodel.LoginViewModel

interface LoginListener{
    fun login(view: View, vm: LoginViewModel, user: LoginUser)
}