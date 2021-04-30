package com.sngs.swayam.edu.ui.promo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Dashboard
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.databinding.ActivityViewPromosBinding
import com.sngs.swayam.edu.ui.promo.adapter.PromoAdapter
import com.sngs.swayam.edu.ui.promo.handler.PromoCallbackListener
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel
import com.sngs.swayam.edu.utils.common.PromoOps
import com.sngs.swayam.edu.utils.common.Utilities
import kotlinx.android.synthetic.main.activity_view_promos.*

class ViewPromosActivity : AppCompatActivity(), PromoCallbackListener {
    private lateinit var vm: PromoViewModel
    private lateinit var adapter: PromoAdapter
    private lateinit var activityViewPromosBinding: ActivityViewPromosBinding
    private lateinit var dashboard: Dashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        vm = ViewModelProvider(this)[PromoViewModel::class.java]

        initAdapter()

        Log.println(Log.INFO, "ViewPromosActivity","fetching promo list ")
        vm.fetchAllPromos()

        vm.promoListLiveData?.observe(this, Observer {
            Log.println(Log.INFO, "ViewPromosActivity","it "+it)
            if (it!=null){
                viewpromos.visibility = View.VISIBLE

                adapter.setData(it as ArrayList<Promo>)
            }else{
                Utilities().showToast(this,"Error fetching promos list")
            }
            progress_promos.visibility = View.GONE
        })
    }

    private fun initAdapter() {
        val intent = intent
        activityViewPromosBinding = DataBindingUtil.setContentView<ActivityViewPromosBinding>(
            this@ViewPromosActivity, R.layout.activity_view_promos
        );

        val recyclerView: RecyclerView = activityViewPromosBinding.viewpromos
        adapter = PromoAdapter(this, vm, intent)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun setDashboard(dashbrd: Dashboard) {
        dashboard = dashbrd
    }

    override fun refreshUI(promoIOps: PromoOps, position: Int?, promo: Promo?) {
        Log.println(Log.INFO, "ViewPromosActivity", "refreshUI position "+position)
        Log.e("Naimee",""+position)
        if(promo!=null)
             Log.e("Naimee"," not null")
        else
            Log.e("Naimee"," null")

        when(promoIOps){
            PromoOps.EDIT -> {
                Log.println(Log.INFO, "ViewPromosActivity", "refreshUI for update op ")
                val i = Intent(this@ViewPromosActivity, PromoEditActivity::class.java)
                i.putExtra("promo", adapter.getData(position!!));
                i.putExtra("position", position!!.toString());
                i.putExtra("dashboard", dashboard);
                startActivity(i)
            }
            PromoOps.DELETE -> {
                Log.println(Log.INFO, "ViewPromosActivity", "refreshUI for delete op ")
                vm.deletePromoLiveData?.observe(this, Observer {
                    if (it!=null){
                        adapter.notifyDataSetChanged() //for local config
                  //      adapter.removeData(position!!)
                    }else{
                        Utilities().showToast(this,"Cannot delete Promo at the moment!")
                    }
                })
            }
        }
    }

    override fun backPressed() {

    }
}