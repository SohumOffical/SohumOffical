package com.sngs.swayam.business.activity.mytractions

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sngs.swayam.business.R
import com.sngs.swayam.business.adapters.mytractions.MyTractionsAdpater
import com.sngs.swayam.business.adapters.notifications.NotificationAdpater
import com.sngs.swayam.business.network.model.PromoDetail.PromoDetailBaseResponse
import com.sngs.swayam.business.network.model.PromotionList.GetCustomerPromotionListBaseResponse
import com.sngs.swayam.business.network.model.PromotionList.PromotionListResult
import com.sngs.swayam.business.network.model.TranscationDetail.TranscationDetailBaseResponse
import com.sngs.swayam.business.network.servicecall.ServiceCall
import com.sngs.swayam.business.network.webUtlis.Links
import kotlinx.android.synthetic.main.activity_my_tractions.*
import kotlinx.android.synthetic.main.activity_my_tractions.ivBack
import kotlinx.android.synthetic.main.activity_promotion_management.*
import kotlinx.android.synthetic.main.loading_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTractionsActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        setContentView(R.layout.activity_my_tractions)

        init()
        click_fun()
    }



    private fun init() {
        val rv_list = findViewById(R.id.rv_list) as RecyclerView
        rv_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        api_calling_for_get_my_tracations()
    }

    private fun click_fun() {
        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun api_calling_for_get_my_tracations()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Swayam App", Context.MODE_PRIVATE)
        val auth_id = sharedPreferences.getString("Auth_ID","")
        val auth_token = sharedPreferences.getString("Auth_Token","")

        loading_layout.setVisibility(View.VISIBLE)
        ServiceCall.callCustomerTransactionDetailList(this, auth_id,auth_token,Links.User_Type)
            .enqueue(object : Callback<TranscationDetailBaseResponse> {
                override fun onResponse(call: Call<TranscationDetailBaseResponse>,
                                        response: Response<TranscationDetailBaseResponse>) {
                    loading_layout.setVisibility(View.GONE)
                    if (response.isSuccessful()) {
                        val success_v = response.body()?.success
                        Links.Transcation_Result.clear()
                        if (success_v?.toInt()==1) {
                            loading_layout.setVisibility(View.GONE)
                            if(response.body()!!.transactionListResult!=null)
                            {
                                Links.Transcation_Result = response.body()!!.transactionListResult
                                rv_list.adapter = MyTractionsAdpater(this@MyTractionsActivity, Links.Transcation_Result)
                                Links.snack_bar(this@MyTractionsActivity,main_layout,response.body()?.message.toString())
                            }
                        }
                        else {
                            loading_layout.setVisibility(View.GONE)
                            Links.snack_bar(this@MyTractionsActivity,main_layout,response.body()?.message.toString())
                        }
                    } else {
                        loading_layout.setVisibility(View.GONE)
                        Links.snack_bar(this@MyTractionsActivity,main_layout,response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<TranscationDetailBaseResponse>, t: Throwable) {
                    loading_layout.setVisibility(View.GONE)
                    Links.snack_bar(this@MyTractionsActivity,main_layout, t.message.toString())
                }
            })
    }

}