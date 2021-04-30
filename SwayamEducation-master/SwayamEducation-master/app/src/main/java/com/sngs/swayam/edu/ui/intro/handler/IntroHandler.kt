package com.sngs.swayam.edu.ui.intro.handler

import android.util.Log
import android.view.View

class IntroHandler(var listener: IntroCallbackListener?=null): IntroListener {
    override fun onLoginIntroClick(view: View) {
        Log.println(Log.INFO, "IntroHandler","LoginIntro cliked on")
        listener?.moveLoginPage()
    }
}