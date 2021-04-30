package com.sngs.swayam.business.activity.notifications

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naimee.swayam.utlis.theme3bottomnavigation.BottomNavigation
import com.sngs.swayam.business.R
import com.sngs.swayam.business.activity.onboarding.promos.PromotionManagementActivity
import com.sngs.swayam.business.activity.profile.EditProfileActivity
import com.sngs.swayam.business.adapters.notifications.NotificationAdpater
import com.sngs.swayam.business.network.model.BaseResponse
import com.sngs.swayam.business.network.model.Notification.NotificationBaseResponse
import com.sngs.swayam.business.network.servicecall.ServiceCall
import com.sngs.swayam.business.network.webUtlis.Links
import kotlinx.android.synthetic.main.activity_my_tractions.*
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.activity_notification.ivBack
import kotlinx.android.synthetic.main.activity_notification.main_layout
import kotlinx.android.synthetic.main.loading_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        set_data()
        click_fun()
        set_bottom_navigation()
    }

    private fun init()
    {
        val rv_notification_list = findViewById(R.id.rv_notification_list) as RecyclerView
        rv_notification_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    private fun set_data() {
        api_calling_for_notification()
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


    private fun api_calling_for_notification()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Swayam App", Context.MODE_PRIVATE)
        val auth_id = sharedPreferences.getString("Auth_ID","")
        val auth_token = sharedPreferences.getString("Auth_Token","")

        loading_layout.setVisibility(View.VISIBLE)

        ServiceCall.callGetNotificationList(this, auth_id,auth_token, Links.User_Type)
                .enqueue(object : Callback<NotificationBaseResponse> {
                    override fun onResponse(call: Call<NotificationBaseResponse>, response: Response<NotificationBaseResponse>)
                    {
                        Links.notification_List.clear()
                        rv_notification_list.adapter = NotificationAdpater(this@NotificationActivity, Links.notification_List)

                        loading_layout.setVisibility(View.GONE)
                        if (response.isSuccessful())
                        {
                            val success_v = response.body()?.success
                            if (success_v?.toInt()==1)
                            {
                                loading_layout.setVisibility(View.GONE)

                                if(response.body()!!.notification!=null)
                                {
                                    Links.notification_List = response.body()!!.notification
                                    rv_notification_list.adapter = NotificationAdpater(this@NotificationActivity, Links.notification_List)
                                }
                            }
                            else
                            {
                                loading_layout.setVisibility(View.GONE)
                                Links.snack_bar(this@NotificationActivity,main_layout,response.body()?.message.toString())
                            }
                        }
                        else
                        {
                            loading_layout.setVisibility(View.GONE)
                            Links.snack_bar(this@NotificationActivity,main_layout,response.body()?.message.toString())
                        }
                    }
                    override fun onFailure(call: Call<NotificationBaseResponse>, t: Throwable) {
                        loading_layout.setVisibility(View.GONE)
                        Links.snack_bar(this@NotificationActivity,main_layout, t.message.toString())
                    }
                })
    }

}
