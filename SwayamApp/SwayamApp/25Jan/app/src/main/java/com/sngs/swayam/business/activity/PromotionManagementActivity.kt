package com.sngs.swayam.business.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.naimee.swayam.utlis.theme3bottomnavigation.BottomNavigation
import kotlinx.android.synthetic.main.activity_promotion_detail.*
import kotlinx.android.synthetic.main.activity_promotion_detail.ivBack
import kotlinx.android.synthetic.main.activity_promotion_management.*
import kotlinx.android.synthetic.main.loading_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.content.ContextCompat.startActivity
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.net.Uri
import com.sngs.swayam.business.R
import com.sngs.swayam.business.network.WebUtlis.Links
import com.sngs.swayam.business.network.model.CustomerDetail.CustomerDetailBaseResponse
import com.sngs.swayam.business.network.servicecall.ServiceCall


class PromotionManagementActivity : AppCompatActivity() {

    var mobile_no : String = ""
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
        setContentView(R.layout.activity_promotion_management)

        init()
        click_fun()
        set_bottom_navigation()
    }

    private fun click_fun() {
        ivBack.setOnClickListener {
            finish()
        }
        view_more_rel.setOnClickListener {
            val intent = Intent(this, PromotionDetailActivity::class.java)
            intent.putExtra("page_type","1")
            startActivity(intent)
        }

        call_now_rel.setOnClickListener {
           // call_on_number()
        }
    }

    private fun init() {
        api_calling_for_customer_detail()
    }

    public fun call_on_number(){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:" + mobile_no)
        startActivity(intent)
    }
    private fun set_bottom_navigation() {

        bottomNavigation.add(BottomNavigation.Model(
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

            if(name.equals("HOME")){
                move_next_page(0)
            }

            if(name.equals("NOTIFICATION")){
                move_next_page(2)
            }

            if(name.equals("ACCOUNT")){
                move_next_page(3)
            }


        }
        Handler().postDelayed(Runnable {
            bottomNavigation.show(ID_MESSAGE,true)

        },800)
    }

    private fun move_next_page(page_no : Int) {
        if(page_no==0){
            finish()
        }
        else  if(page_no==2){
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
            finish()
        }
        else  if(page_no==3){
            val intent = Intent(this, EditProfileActivity::class.java)
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
                            Links.snack_bar(this@PromotionManagementActivity,main_promotion_layout,response.body()?.message.toString())
                        }
                    } else {
                        Links.snack_bar(this@PromotionManagementActivity,main_promotion_layout,response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<CustomerDetailBaseResponse>, t: Throwable) {
                    loading_layout.setVisibility(View.GONE)
                    Links.snack_bar(this@PromotionManagementActivity,main_promotion_layout,t.message.toString())
                }
            })
    }

    private fun set_data(response: Response<CustomerDetailBaseResponse>) {
        Links.Banner_list.clear()

        if(!response.body()!!.customerResult.customerMedia1.isEmpty())
            Links.Banner_list.add(""+ response.body()!!.customerResult.customerMedia1)

        if(!response.body()!!.customerResult.customerMedia2.isEmpty())
            Links.Banner_list.add(""+ response.body()!!.customerResult.customerMedia2)

        if(!response.body()!!.customerResult.customerMedia3.isEmpty())
            Links.Banner_list.add(""+ response.body()!!.customerResult.customerMedia3)

        if(!response.body()!!.customerResult.customerMedia4.isEmpty())
            Links.Banner_list.add(""+ response.body()!!.customerResult.customerMedia4)


        Glide.with(applicationContext).load(Links.Banner_list.get(0)).into(shop_img);

        mobile_no = response.body()!!.customerResult.customerContactNumber

        pro_shop_name_txt.setText(""+ response.body()!!.customerResult.customerName)
        pro_shop_detail_txt.setText(""+ response.body()!!.customerResult.customerShopAddress1+","
                + response.body()!!.customerResult.customerShopAddress2+","
                + response.body()!!.customerResult.customerShopArea+","
                + response.body()!!.customerResult.customerShopCity+","
                + response.body()!!.customerResult.customerShopPincode+","
                + response.body()!!.customerResult.customerShopState)


        shop_detail_rel.visibility = View.VISIBLE
    }

}
