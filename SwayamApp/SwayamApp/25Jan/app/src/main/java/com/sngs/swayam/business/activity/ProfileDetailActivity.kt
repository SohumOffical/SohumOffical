package com.sngs.swayam.business.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide.init
import com.google.android.material.snackbar.Snackbar
import com.sngs.swayam.business.R
import com.sngs.swayam.business.network.WebUtlis.Links
import com.sngs.swayam.business.network.model.BaseResponse
import com.sngs.swayam.business.network.model.CustomerDetail.CustomerDetailBaseResponse
import com.sngs.swayam.business.network.servicecall.ServiceCall
import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import kotlinx.android.synthetic.main.activity_profile_details.*
import kotlinx.android.synthetic.main.loading_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.ArrayList

class ProfileDetailActivity : AppCompatActivity() {


    var MAX_ATTACHMENT_COUNT = 1
    var profile_File : File? = null
    var select_profile_type = "Company"
    var emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        setContentView(R.layout.activity_profile_details)


        init()
        click_fun()

    }

    private fun init() {
        api_calling_for_customer_detail()
    }

    private fun click_fun() {
        ivBack.setOnClickListener {
            finish()
        }

        btncompany.setOnClickListener {
            btncompany.setBackgroundResource(R.drawable.contact_shape)
            btnindividual.setBackgroundResource(R.drawable.contact_shape_deselected)

            btncompany.setTextColor(resources.getColor(R.color.white))
            btnindividual.setTextColor(resources.getColor(R.color.black))
            select_profile_type = "Company"
        }
        btnindividual.setOnClickListener {
            btncompany.setBackgroundResource(R.drawable.contact_shape_deselected)
            btnindividual.setBackgroundResource(R.drawable.contact_shape)

            btncompany.setTextColor(resources.getColor(R.color.black))
            btnindividual.setTextColor(resources.getColor(R.color.white))
            select_profile_type = "Individual"
        }

        next_img.setOnClickListener {
            validation()

        }

        et_state.setOnClickListener {
            val intent = Intent(this, State_City_List_Activity::class.java)
            intent.putExtra("page_type","0")
            startActivity(intent)
        }

        et_cities.setOnClickListener {
            val intent = Intent(this, State_City_List_Activity::class.java)
            intent.putExtra("page_type","1")
            startActivity(intent)
        }

        et_service_area.setOnClickListener {
            val intent = Intent(this, State_City_List_Activity::class.java)
            intent.putExtra("page_type","2")
            startActivity(intent)
        }
        profile_rel2.setOnClickListener {
            //onPickPhoto()
        }
    }

    private fun move_next_page() {
        val intent = Intent(this, BusinessDetailsActivity::class.java)
        startActivity(intent)
    }

    fun onPickPhoto() {
        Links.selected_image_array_list.clear()
        try {
            val maxCount: Int = MAX_ATTACHMENT_COUNT
            if (Links.selected_image_array_list.size == MAX_ATTACHMENT_COUNT) {
                Toast.makeText(
                    this@ProfileDetailActivity,
                    "Cannot select more than $MAX_ATTACHMENT_COUNT items",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FilePickerBuilder.instance
                    .setMaxCount(maxCount)
                    .setSelectedFiles(Links.selected_image_array_list)
                    .setActivityTheme(R.style.FilePickerTheme)
                    .setActivityTitle("Select Image / Video")
                    .showGifs(false)
                    .setMaxCount(1)
                    .enableImagePicker(true)
                    .enableVideoPicker(true)
                    .showFolderView(true)
                    .enableSelectAll(true)
                    .setCameraPlaceholder(R.drawable.ic_camera)
                    .withOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .pickPhoto(this)
            }
        } catch (e: java.lang.Exception) {}
    }

    private fun validation() {
        if(et_Name.text.toString().isEmpty()){
            clear_error()
            tl_Name.error = resources.getString(R.string.name_error)
        }
        else if(et_tl_alt_mobile.text.toString().isEmpty()){
            clear_error()
            tl_alt_mobile.error = resources.getString(R.string.mobile_error)
        }
        else if(et_tl_alt_mobile.text.toString().length<10){
            clear_error()
            tl_alt_mobile.error = resources.getString(R.string.mobile_lenght_error)
        }
        else if(!et_email.text.toString().matches(Regex(emailPattern))){
            clear_error()
            tl_email.error = resources.getString(R.string.email_validation)
        }
        else if(et_address_line_1.text.toString().isEmpty()){
            clear_error()
            tl_address_line_1.error = resources.getString(R.string.address_line_1_error)
        }
        /*else if(et_address_line_2.text.toString().isEmpty()){
            clear_error()
            tl_address_line_2.error = resources.getString(R.string.address_line_2_error)
        }*/
        else if(et_state.text.toString().isEmpty()){
            clear_error()
            tl_state.error = resources.getString(R.string.state_error)
        }
        else if(et_cities.text.toString().isEmpty()){
            clear_error()
            tl_cities.error = resources.getString(R.string.cities_error)
        }
        else if(et_service_area.text.toString().isEmpty()){
            clear_error()
            tl_service_area.error = resources.getString(R.string.service_area_error)
        }
        else if(et_pincode.text.toString().isEmpty()){
            clear_error()
            tl_pincode.error = resources.getString(R.string.pincode_error)
        }
        else{
            api_calling_for_customer_profile_detail()
        }
    }

    private fun clear_error() {
        tl_Name.error = null
        tl_alt_mobile.error = null
        tl_email.error = null
        tl_address_line_1.error = null
        tl_address_line_2.error = null
        tl_state.error = null
        tl_cities.error = null
        tl_service_area.error = null
        tl_pincode.error = null
        tl_employe_code.error = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
            Links.selected_image_array_list.clear()
            Links.selected_image_array_list.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA)!!)

            addThemToView(Links.selected_image_array_list)
        }

    }

    private fun addThemToView(imagePaths: ArrayList<String>?) {
        val filePaths = ArrayList<String>()

        if (imagePaths != null)
            filePaths.addAll(imagePaths)

        val mStringArray: Array<Any> = filePaths.toTypedArray()

        Log.e("selectedImagePath", "" + imagePaths!!.size)

        for (i in mStringArray.indices) {
            Log.e("selectedImagePath", "" + mStringArray[i] as String)
            val qc_report_string = mStringArray[i] as String

            profile_File = File(qc_report_string)
            if (profile_File != null) {
                Log.e("", "not null")
                val uri = Uri.fromFile(profile_File)
                img2.setImageURI(uri)
            }
            else {
                Log.e("selectedImagePath", "null")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        et_service_area.setText(""+ Links.selected_servies_name)
        et_cities.setText(""+ Links.selected_city_name)
        et_state.setText(""+ Links.selected_state_name)

        if(!Links.selected_state_name.isEmpty()){
            tl_cities.visibility = View.VISIBLE
        }

        if(!Links.selected_city_name.isEmpty()){
            tl_service_area.visibility = View.VISIBLE
        }

    }

    private fun api_calling_for_customer_profile_detail()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Swayam App", Context.MODE_PRIVATE)
        val auth_id = sharedPreferences.getString("Auth_ID","")
        val auth_token = sharedPreferences.getString("Auth_Token","")

        loading_layout.setVisibility(View.VISIBLE)

        ServiceCall.callCustomerProfileDetail(this, auth_id, auth_token, Links.User_Type,
            select_profile_type, et_Name.text.toString(),
            et_tl_alt_mobile.text.toString(), et_email.text.toString(), et_address_line_1.text.toString(),
            et_address_line_2.text.toString(), et_state.text.toString(), et_cities.text.toString(),
            et_service_area.text.toString(), et_pincode.text.toString(), profile_File, "India")
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    loading_layout.setVisibility(View.GONE)
                    if (response.isSuccessful()) {
                        val success_v = response.body()?.success

                        if (success_v?.toInt()==1)
                        {
                            Links.snack_bar(this@ProfileDetailActivity,main_layout,response.body()?.message.toString())
                            move_next_page()
                        }
                        else {
                            Links.snack_bar(this@ProfileDetailActivity,main_layout,response.body()?.message.toString())
                        }
                    } else {
                        Links.snack_bar(this@ProfileDetailActivity,main_layout,response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    loading_layout.setVisibility(View.GONE)
                    Links.snack_bar(this@ProfileDetailActivity,main_layout,t.message)
                }
            })
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
                            Links.snack_bar(this@ProfileDetailActivity,main_layout,response.body()?.message.toString())
                        }
                    } else {
                        Links.snack_bar(this@ProfileDetailActivity,main_layout,response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<CustomerDetailBaseResponse>, t: Throwable) {
                    loading_layout.setVisibility(View.GONE)
                    Links.snack_bar(this@ProfileDetailActivity,main_layout,t.message)
                }
            })
    }

    private fun set_data(response: Response<CustomerDetailBaseResponse>) {
        et_Name.setText(""+ response.body()!!.customerResult.customerName)
        et_tl_alt_mobile.setText(""+ response.body()!!.customerResult.customerContactNumber)
    }

}
