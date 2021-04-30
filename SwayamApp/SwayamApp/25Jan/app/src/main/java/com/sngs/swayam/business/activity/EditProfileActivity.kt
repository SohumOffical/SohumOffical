package com.sngs.swayam.business.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.naimee.swayam.utlis.theme3bottomnavigation.BottomNavigation
import com.sngs.swayam.business.R
import com.sngs.swayam.business.network.WebUtlis.Links
import com.sngs.swayam.business.network.model.CustomerDetail.CustomerDetailBaseResponse
import com.sngs.swayam.business.network.servicecall.ServiceCall
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.et_Mobile
import kotlinx.android.synthetic.main.activity_edit_profile.et_Name
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.loading_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_edit_profile)


        init()
        set_bottom_navigation()
        click_fun()
    }

    private fun init() {
        api_calling_for_customer_detail()
    }

    private fun click_fun() {
        ivBack.setOnClickListener {
            finish()
        }
        btnsubmit.setOnClickListener {
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

            if(name.equals("NOTIFICATION")){
                move_next_page(2)
            }

            if(name.equals("HOME")){
                move_next_page(0)
            }
        }
        Handler().postDelayed(Runnable {
            bottomNavigation.show(ID_ACCOUNT,true)

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
        else  if(page_no==2){
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun api_calling_for_customer_detail()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Swayam App", Context.MODE_PRIVATE)
        val auth_id = sharedPreferences.getString("Auth_ID","")
        val auth_token = sharedPreferences.getString("Auth_Token","")

        loading_layout.setVisibility(View.VISIBLE)

        ServiceCall.callCustomerDetail(this, auth_id , auth_token , Links.User_Type)
            .enqueue(object : Callback<CustomerDetailBaseResponse> {
                override fun onResponse(call: Call<CustomerDetailBaseResponse>, response: Response<CustomerDetailBaseResponse>) {
                    loading_layout.setVisibility(View.GONE)
                    if (response.isSuccessful()) {
                        val success_v = response.body()?.success
                        if (success_v?.toInt()==1) {
                            set_data(response)
                        }
                        else {
                            Links.snack_bar(this@EditProfileActivity,main_layout,response.body()?.message.toString())
                        }
                    } else {
                        Links.snack_bar(this@EditProfileActivity,main_layout,response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<CustomerDetailBaseResponse>, t: Throwable) {
                    loading_layout.setVisibility(View.GONE)
                    Links.snack_bar(this@EditProfileActivity,main_layout,t.message.toString())
                }
            })
    }

    private fun set_data(response: Response<CustomerDetailBaseResponse>)
    {
        et_Name.setText(""+ response.body()!!.customerResult.customerName)
        et_Mobile.setText(""+ response.body()!!.customerResult.customerContactNumber)
    }
}
