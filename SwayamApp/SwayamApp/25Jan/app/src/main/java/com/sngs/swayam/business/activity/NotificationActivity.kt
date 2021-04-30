package com.sngs.swayam.business.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.naimee.swayam.utlis.theme3bottomnavigation.BottomNavigation
import com.sngs.swayam.business.R
import com.sngs.swayam.business.adapters.NotificationAdpater
import com.sngs.swayam.business.network.WebUtlis.Links
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    companion object {
        private const val ID_HOME = 1
        private const val ID_MESSAGE = 3
        private const val ID_NOTIFICATION = 4
        private const val ID_ACCOUNT = 5
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        setContentView(R.layout.activity_notification)

        init()
        click_fun()
        set_bottom_navigation()
    }


    private fun init() {
        Links.notification_list.clear();
        Links.notification_list.add("");
        Links.notification_list.add("");
        Links.notification_list.add("");

        val rv_list = findViewById(R.id.rv_list) as RecyclerView
        rv_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_list.adapter = NotificationAdpater(this@NotificationActivity,Links.notification_list)

    }

    private fun click_fun() {
        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun set_bottom_navigation() {

        bottomNavigation.add(
            BottomNavigation.Model(
            ID_HOME,
            R.drawable.ic_home
        ))
        bottomNavigation.add(
            BottomNavigation.Model(
                ID_MESSAGE,
                R.drawable.ic_heart
            ))
        bottomNavigation.add(BottomNavigation.Model(
            ID_NOTIFICATION,
            R.drawable.ic_notification
        ))
        bottomNavigation.add(BottomNavigation.Model(
            ID_ACCOUNT,
            R.drawable.ic_account
        ))

        bottomNavigation.setOnShowListener {
            val name = when (it.id) {
                ID_HOME -> "HOME"
                ID_MESSAGE -> "My ADS"
                ID_NOTIFICATION -> "NOTIFICATION"
                ID_ACCOUNT -> "ACCOUNT"
                else -> ""
            }
        }


        bottomNavigation.setOnClickMenuListener {
            val name = when (it.id) {
                ID_HOME -> "HOME"
                ID_MESSAGE -> "My ADS"
                ID_NOTIFICATION -> "NOTIFICATION"
                ID_ACCOUNT -> "ACCOUNT"
                else -> ""
            }

            if(name.equals("My ADS")){
                move_next_page(1)
            }

            if(name.equals("HOME")){
                move_next_page(0)
            }

            if(name.equals("ACCOUNT")){
                move_next_page(3)
            }


        }
        Handler().postDelayed(Runnable {
            bottomNavigation.show(ID_NOTIFICATION,true)

        },800)
    }

    private fun move_next_page(page_no : Int) {
        if(page_no==0){
            finish()
        }
        else  if(page_no==1){
            val intent = Intent(this, PromotionManagementActivity::class.java)
            startActivity(intent)
            finish()
        }
        else  if(page_no==3){
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
