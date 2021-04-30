package com.sngs.swayam.edu.utils.common

import android.content.Context
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import androidx.core.content.ContextCompat.getSystemService

class Utilities {

    fun showToast(context: Context, msg:String){
       Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }

    fun withDelay(delay: Long = 1000, block: () -> Unit) {
        Handler().postDelayed(Runnable(block), delay)
    }
}