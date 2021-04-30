package com.sngs.swayam.edu.ui.login.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.LoginUser
import com.sngs.swayam.edu.data.repository.LoginRepository

class LoginViewModel(application: Application): AndroidViewModel(application){
    private var loginRepository: LoginRepository?=null
    var userValidateLiveData: LiveData<Boolean>?=null

    init {
        loginRepository = LoginRepository()
        userValidateLiveData = MutableLiveData<Boolean>()
    }

    fun validateUser(user: LoginUser){
        Log.println(Log.INFO, "LoginViewModel","validating user details ")
        userValidateLiveData = loginRepository?.validateUser(user);
    }
}