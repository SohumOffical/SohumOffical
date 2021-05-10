package com.totemsoft.screenlookcount.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi
import com.totemsoft.screenlookcount.db.DayLookEntity
import com.totemsoft.screenlookcount.db.ScreenCounterDb
import com.totemsoft.screenlookcount.utils.C
import com.totemsoft.screenlookcount.utils.toPatternString
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import java.util.*


class ScreenLookReceiver : BroadcastReceiver() {

    var countDownTimer: CountDownTimer? = null

//    companion object {
        var c=0
//    }


    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    private fun increaseScreenLookCountForTheDay(context: Context, intent: Intent) {
        val action = intent.action

        var screenOnCount = 0
        var screenOffCount = 0
        var screenUnlockCount = 0
        var time=0.toLong()
        var start=0.toLong()
        var end:Long

        var d1= Date(0)
        var d2:Date

        when (action) {
            Intent.ACTION_SCREEN_ON -> {
                screenOnCount += 1
                start = System.currentTimeMillis();
                d1=Date(start)
                c=0
            }
            Intent.ACTION_SCREEN_OFF -> {
                screenOffCount += 1
            }

            Intent.ACTION_USER_PRESENT -> {
                screenUnlockCount += 1
            }
            else -> {
            }
        }

         countDownTimer = object : CountDownTimer(10, 10) {
            override fun onTick(millisUntilFinished: Long) {

                val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager?
                val isScreenOn = pm!!.isInteractive

                //val isScreenOn = if (Utils.hasLollipop()) pm!!.isInteractive else pm!!.isScreenOn

                if(!isScreenOn){

                    if(c==0){

                        c++

                        end=System.currentTimeMillis();

                        d2=Date(end)

                        time=d2.time-d1.time


                        action?.let {
                            val nowDateString = Date().toPatternString(context)
                            ScreenCounterDb.getDatabase(context)
                                    .getDayLook(nowDateString)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(Schedulers.io())
                                    .subscribeBy(
                                            onSuccess = {
                                                // update existing daylook

                                                with(it) {

                                                    countDownTimer?.cancel()


                                                    Log.d("screenoncounter",""+Math.round((time/1000).toFloat()));


                                                    //screenon count
                                                       screenon =if (screenon != null) screenon!!.plus(screenOnCount) else 0


                                                     //totaltime count

                                                    if(time.toInt()/(24*60*60*1000)<5){
                                                        screenoff = if (screenoff != null) screenoff!!.plus(Math.round((time/1000).toFloat())) else 0

                                                        daywise = if (daywise != null) daywise+Math.round((time/1000).toFloat())+" " else ""

                                                    }

                                                        screenunlock = if (screenunlock != null) screenunlock!!.plus(screenUnlockCount) else 0
                                                        addLooks(context, this)


                                                }
                                            },
                                            onComplete = {
                                                // create a new daylook

                                                val dayLook = DayLookEntity()
                                                with(dayLook) {
                                                    date = nowDateString
                                                    screenon = screenOnCount
                                                    screenoff = screenOffCount
                                                    screenunlock = screenUnlockCount
                                                    daywise=""
                                                    addLooks(context, this)
                                                }
                                            },
                                            onError = {
                                            }
                                    )
                        }

                    }
                }
            }

            override fun onFinish() {
                if(c==0){
                    countDownTimer?.start()
                }
            }
        }

        countDownTimer?.start()

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }

        increaseScreenLookCountForTheDay(context, intent)
    }

    private fun addLooks(context: Context, dayLook: DayLookEntity) {
        ScreenCounterDb.getDatabase(context).addDayLook(dayLook)
    }
}
