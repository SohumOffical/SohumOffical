package com.sngs.swayam.business.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.sngs.swayam.business.users.LoginActivity
import com.naimee.swayam.utlis.theme3bottomnavigation.BottomNavigation
import com.sngs.swayam.business.R
import com.sngs.swayam.business.network.WebUtlis.Links
import com.sngs.swayam.business.network.model.CustomerDetail.CustomerDetailBaseResponse
import com.sngs.swayam.business.network.servicecall.ServiceCall
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.loading_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_drawer)

        init()
        set_drawer()
        click_drawer()
    }

    private fun click_drawer() {
        logout_li1.setOnClickListener {
            logout()
        }

        profile_li.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {


        bottomNavigation.add(BottomNavigation.Model(
            ID_HOME,
            R.drawable.ic_home
        ))
        bottomNavigation.add(BottomNavigation.Model(
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

            if(name.equals("ACCOUNT")){
                move_next_page(3)
            }


        }
        Handler().postDelayed(Runnable {
            bottomNavigation.show(ID_HOME,true)

        },800)


        api_calling_for_customer_detail()
    }

    fun set_drawer() {

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawerlayout,
            R.string.open,
            R.string.close
        ) {
            private val scaleFactor = 10f
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
                content.translationX = slideX
                content.scaleX = 1 - slideOffset / scaleFactor
                content.scaleY = 1 - slideOffset / scaleFactor
            }
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                btnMenu.setImageResource(R.drawable.ic_drawer)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                btnMenu.setImageResource(R.drawable.ic_back_arrow)
            }
        }

        drawerlayout.setScrimColor(Color.TRANSPARENT)
        drawerlayout.drawerElevation = 0f
        drawerlayout.addDrawerListener(actionBarDrawerToggle)

        btnMenu.setOnClickListener {
            drawerlayout.openDrawer(GravityCompat.START);
            setStatusBarGradient()
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
                            Links.snack_bar(this@HomeActivity,content,response.body()?.message.toString())
                        }
                    } else {
                        Links.snack_bar(this@HomeActivity,content,response.body()?.message.toString())
                    }
                }
                override fun onFailure(call: Call<CustomerDetailBaseResponse>, t: Throwable) {
                    loading_layout.setVisibility(View.GONE)
                    Links.snack_bar(this@HomeActivity,content,t.message.toString())
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


        user_name_txt.setText(""+ response.body()!!.customerResult.customerName)
        tv_user_name.setText(""+ response.body()!!.customerResult.customerName)
        tv_user_mobile.setText(""+ response.body()!!.customerResult.customerContactNumber)
        address_txt.setText(""+ response.body()!!.customerResult.customerShopCity)
        number_txt.setText(""+ response.body()!!.customerResult.customerContactNumber)

        tv_user_shop_code.setText(""+ response.body()!!.customerResult.getmCustomerNumber())

        val adapter = WalkAdapter()
        viewpager.adapter = adapter
        dots.setViewPager(viewpager)
        viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

    }

    fun setStatusBarGradient(color: Int = R.color.white) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val window = window
                var flags = window.decorView.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.decorView.systemUiVisibility = flags
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(this, color)
            }
            else -> {
                window.statusBarColor =  ContextCompat.getColor(this,
                    R.color.view_color
                )
            }
        }
    }

    internal inner class WalkAdapter : PagerAdapter() {
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(container.context).inflate(R.layout.layout_viewpager, container, false)

            Glide.with(applicationContext).load(Links.Banner_list.get(position)).into((view.findViewById(R.id.img_banner) as ImageView));

            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return  Links.Banner_list.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as View
        }
    }

    private fun move_next_page(page_no : Int) {
        if(page_no==1){
            val intent = Intent(this, PromotionManagementActivity::class.java)
            startActivity(intent)
        }
        else  if(page_no==2){
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        else  if(page_no==3){
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.show(ID_HOME,true)
    }

    private  fun logout()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("Swayam App", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("Is_Login","false")
        editor.commit()
        finish()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(Gravity.LEFT)
        } else {
            back_dialog_box()
        }
    }

    private fun back_dialog_box(){
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.app_name)

        //set message for alert dialog
        builder.setMessage(R.string.back_mes)
        builder.setIcon(R.drawable.app_logo)

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            finish()
        }

        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->

        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}
