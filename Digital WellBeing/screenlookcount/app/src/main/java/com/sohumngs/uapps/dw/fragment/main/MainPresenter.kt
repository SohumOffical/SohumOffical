package com.sohumngs.uapps.dw.fragment.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sohumngs.uapps.dw.BasePresenter
import com.sohumngs.uapps.dw.db.ScreenCounterDb
import com.sohumngs.uapps.dw.fragment.calendar.FragmentCalendar
import com.sohumngs.uapps.dw.utils.C
import com.sohumngs.uapps.dw.utils.showDialog
import com.sohumngs.uapps.dw.utils.toPatternString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList



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


    @RequiresApi(Build.VERSION_CODES.O)
    fun setCountersValues(context: Context, nowDateString: Date, type: String) {



        if(type.equals("firstpickup")){


            var arr: LongArray= longArrayOf(0,1,2,3,4,5,6)


            var x=0;
            var min=0.toLong()
            var max=0.toLong()
            var date: LocalDate = LocalDate.now().minusDays(0)
            var date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            var data=ArrayList<Long>()


            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[0]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[0]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[1]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[0]=min

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[2]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[2]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }


                                arr[3]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[3]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[4]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[4]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[5]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[5]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val firstpickup=it.firstpickup

                                val words = firstpickup.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[6]=min

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[6]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )


        }



        if(type.equals("lastdropoff")){

            var arr: LongArray= longArrayOf(0,1,2,3,4,5,6)


            var x=0;
            var min=0.toLong()
            var max=0.toLong()
            var date: LocalDate = LocalDate.now().minusDays(0)
            var date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            var data=ArrayList<Long>()


            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }


                                arr[0]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)




                            },
                            onComplete = {


                                arr[0]=0

                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[1]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[1]=0


                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[2]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)




                            },
                            onComplete = {

                                arr[2]=0


                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[3]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)




                            },
                            onComplete = {

                                arr[3]=0


                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[4]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)




                            },
                            onComplete = {

                                arr[4]=0


                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[5]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)




                            },
                            onComplete = {

                                arr[5]=0


                                getCurrentView()?.setCountersView(x, type, min,max,arr)

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


                                val dropoff=it.lastdropoff

                                val words = dropoff.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(x.toLong())
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
                                    if(i<min){
                                        min=i
                                    }
                                    if(i>max){
                                        max=i
                                    }
                                }

                                arr[6]=max

                                getCurrentView()?.setCountersView(x, type,min,max,arr)


                            },
                            onComplete = {

                                arr[6]=0


                                getCurrentView()?.setCountersView(x, type, min,max,arr)

                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

        }


        if(type.equals("longest")){

            var arr: IntArray=intArrayOf(0,1,2,3,4,5,6)
            var arr_1: IntArray=intArrayOf(0,1,2,3,4,5,6)


            var y=0;
            var min=0
            var max=0
            var date: LocalDate = LocalDate.now().minusDays(0)
            var date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            var data=ArrayList<Int>()

            var diff=ArrayList<Int>()


            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[0]=arr_1[0]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()
                                    }catch (e:Exception){

                                    }

                                }

                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {

                                arr_1[0]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

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

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0;
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[1]=arr_1[1]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()

                                    }catch (e:Exception){

                                    }

                                }

                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {

                                arr_1[1]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

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

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0;
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[2]=arr_1[2]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()

                                    }catch (e:Exception){

                                    }

                                }
                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {


                                arr_1[2]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

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

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0;
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[3]=arr_1[3]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()

                                    }catch (e:Exception){

                                    }

                                }

                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {

                                arr_1[3]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

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

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0;
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[4]=arr_1[4]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()

                                    }catch (e:Exception){

                                    }

                                }

                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {

                                arr_1[4]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

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

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0;
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[5]=arr_1[5]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()

                                    }catch (e:Exception){

                                    }

                                }

                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {

                                arr_1[5]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

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

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                        y=y+Integer.parseInt(x);
                                    }catch (e:Exception){

                                    }
                                }


                                val offscreen=it.offscreen

                                val words_1 = offscreen.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }


                                var data_1=ArrayList<Long>()


                                for(x in words_1){

                                    try {

                                        data_1.add(x.toLong())

                                    }catch (e:Exception){

                                    }
                                }



                                var i=0;
                                for (item in data_1) {

                                    try {
                                        diff.add(((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt())
                                        i=i+2
                                        arr_1[6]=arr_1[6]+((data_1.get(i + 2) - data_1.get(i + 1))/1000).toInt()

                                    }catch (e:Exception){

                                    }

                                }

                                try {
                                    min = diff.get(0)
                                    max = diff.get(0)
                                }catch (e:Exception){

                                }

                                for(item in diff){

                                    if(item>max){
                                        max=item
                                    }

                                    if(item<min){
                                        min=item
                                    }
                                }


                                getCurrentView()?.setCountersView(y, type,min,max,arr,arr_1)


                            },
                            onComplete = {


                                arr_1[6]=0

                                getCurrentView()?.setCountersView(y, type,0,max,arr,arr_1)

                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )

        }


        if(type.equals("averageuse")){

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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())

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
                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
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



        if(type.equals("pickups")){


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


        if(type.equals("today")){

            var arr: IntArray=intArrayOf(0,1,2,3,4,5,6)


            var x=0;
            var min=0
            var max=0
            var date: LocalDate = LocalDate.now().minusDays(0)
            var date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            var data=ArrayList<Int>()


            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {

                                val time= Integer.parseInt((it.screenoff ?: 0).toString())

                                val count= Integer.parseInt((it.screenon ?: 0).toString())

                                arr[0]=0;


                                getCurrentView()?.setCountersView(0,type,time,count,arr)


                            },
                            onComplete = {

                                getCurrentView()?.setCountersView(0, type,0,0,arr)

                            },
                            onError = {
                                Log.e(C.TAG, "setCountersValues() error: ${it.message}")
                            }
                    )


        }

        if(type.equals("total")){


            var arr: IntArray=intArrayOf(0,1,2,3,4,5,6)


            var x=0;
            var min=0
            var max=0
            var date: LocalDate = LocalDate.now().minusDays(0)
            var date_1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            var data=ArrayList<Int>()


            ScreenCounterDb.getDatabase(context as AppCompatActivity)
                    .getDayLook(date_1.toPatternString(context))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {



                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
                                arr[0]=y
                                x=x+y


                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }

                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}


                                for(i in data){
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())

                                arr[1]=y
                                x=x+y

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }


                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}

                                for(i in data){
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
                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
                                arr[2]=y
                                x=x+y;

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }


                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}

                                for(i in data){
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
                                arr[3]=y
                                x=x+y


                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }


                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}

                                for(i in data){
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
                                arr[4]=y
                                x=x+y
                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }


                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}

                                for(i in data){
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
                                arr[5]=y
                                x=x+y

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }


                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}

                                for(i in data){
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

                                val y= Integer.parseInt((it.screenoff ?: 0).toString())
                                arr[6]=y
                                x=x+y

                                val daywise=it.daywise

                                val words = daywise.split("\\s+".toRegex()).map { word ->
                                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                                }

                                for(x in words){

                                    try {
                                        data.add(Integer.parseInt(x))
                                    }catch (e:Exception){

                                    }
                                }


                                try{
                                    min =data.get(0)
                                    max=data.get(0)
                                }catch (e:Exception){}

                                for(i in data){
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