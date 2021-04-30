package com.sngs.swayam.edu.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sngs.swayam.edu.data.model.LoginUser

class LoginRepository {
    fun validateUser(user: LoginUser): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        //ToDo: needs to be repalced with api call
        if(user.email.equals("1234567890") && user.pwd.equals("1234"))
            data.value = true;
        else
            data.value = false;
        return data
    }
}