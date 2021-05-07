package com.totemsoft.screenlookcount.fragment.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.totemsoft.screenlookcount.BasePresenter
import com.totemsoft.screenlookcount.db.ScreenCounterDb
import com.totemsoft.screenlookcount.fragment.calendar.FragmentCalendar
import com.totemsoft.screenlookcount.utils.C
import com.totemsoft.screenlookcount.utils.showDialog
import com.totemsoft.screenlookcount.utils.toPatternString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalQueries.localDate
import java.util.*


/**
 * Presenter for main (home) fragment
 *
 * @author Antonina
 */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun showCalendarWithLooks(context: Context) {
        val bundle = Bundle()
        bundle.putString(C.DIALOG_TAG_STAT_CAT, C.BUNDLE_ARG_SCREEN_LOOK)
        (context as AppCompatActivity).showDialog(FragmentCalendar::class.java, C.DIALOG_TAG_STAT, bundle)
    }

    override fun showCalendarWithUnlocks(context: Context) {
        val bundle = Bundle()
        bundle.putString(C.DIALOG_TAG_STAT_CAT, C.BUNDLE_ARG_SCREEN_UNLOCK)
        (context as AppCompatActivity).showDialog(FragmentCalendar::class.java, C.DIALOG_TAG_STAT, bundle)
    }

    /**
     * Sets screen view and screen unlock numbers, taken from the database.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun setCountersValues(context: Context, nowDateString: Date, type: String) {


//        val nowDateString = Date().toPatternString(context)

        if(type.equals("total")){


            var arr: IntArray=intArrayOf(0,1,2,3,4,5,6)


            var x=0;
            var min=0
            var max=0
            var date: LocalDate = LocalDate.now().minusDays(0)
            var date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val y= Integer.parseInt((it.screenon ?: 0).toString())
                                arr[0]=y
                                x=x+y

                                min=arr[0]
                                max=arr[0]
                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[0]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

            date = LocalDate.now().minusDays(1)
            date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val y= Integer.parseInt((it.screenon ?: 0).toString())

                                arr[1]=y
                                x=x+y

                                if(arr[0]>arr[1]){
                                    min=arr[1]
                                    max=arr[0]
                                }else{
                                    max=arr[1]
                                    min=arr[0]
                                }
                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[1]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )


            date = LocalDate.now().minusDays(2)
            date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                var min=0
                                var max=0
                                val y= Integer.parseInt((it.screenon ?: 0).toString())
                                arr[2]=y
                                x=x+y;

                                min =arr[0]
                                max=arr[0]
                                for(i in arr){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[2]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

            date = LocalDate.now().minusDays(3)
            date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val y= Integer.parseInt((it.screenon ?: 0).toString())
                                arr[3]=y
                                x=x+y


                                min =arr[0]
                                max=arr[0]
                                for(i in arr){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[3]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

            date = LocalDate.now().minusDays(4)
            date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val y= Integer.parseInt((it.screenon ?: 0).toString())
                                arr[4]=y
                                x=x+y

                                min =arr[0]
                                max=arr[0]
                                for(i in arr){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[4]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

            date = LocalDate.now().minusDays(5)
            date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val y= Integer.parseInt((it.screenon ?: 0).toString())
                                arr[5]=y
                                x=x+y

                                min =arr[0]
                                max=arr[0]
                                for(i in arr){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[5]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

            date = LocalDate.now().minusDays(6)
            date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())

            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val y= Integer.parseInt((it.screenon ?: 0).toString())
                                arr[6]=y
                                x=x+y

                                min =arr[0]
                                max=arr[0]
                                for(i in arr){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                getCurrentView()?.setCountersView(x, type,min,max,arr)

                            },
                            onComplete = {
                                arr[6]=0
                                getCurrentView()?.setCountersView(x, type,0,max,arr)
                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )


        }



    }
}