package com.sngs.swayam.edu.data.model

data class LoginUser(var email:String?="", var pwd:String?="") {
    override fun toString(): String {
        return "LoginUser(email=$email, pwd=$pwd)"
    }
}