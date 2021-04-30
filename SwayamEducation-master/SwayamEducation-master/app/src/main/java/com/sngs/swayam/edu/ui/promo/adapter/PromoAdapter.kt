package com.sngs.swayam.edu.ui.promo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sngs.swayam.edu.R
import com.sngs.swayam.edu.data.model.Promo
import com.sngs.swayam.edu.databinding.ActivityPromoListBinding
import com.sngs.swayam.edu.ui.promo.handler.PromoCallbackListener
import com.sngs.swayam.edu.ui.promo.handler.PromoHandler
import com.sngs.swayam.edu.ui.promo.viewmodel.PromoViewModel

class PromoAdapter(var listener:PromoCallbackListener, var vm: PromoViewModel,  var intent: Intent) : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>(){

    private var data: ArrayList<Promo>? = null

    fun setData(list: ArrayList<Promo>){
        data = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val activityPromoListBinding: ActivityPromoListBinding =
            DataBindingUtil.inflate<ActivityPromoListBinding>(
                LayoutInflater.from(parent.context), R.layout.activity_promo_list, parent, false)

        val holder = PromoViewHolder(activityPromoListBinding)
        return holder
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val item = data?.get(position)
        holder.promoListBinding.dashboard = intent.getParcelableExtra("dashboard")!!
        holder.promoListBinding.promo = item!!
        holder.promoListBinding.viewmodel = vm
        if(holder.promoListBinding.handler==null)
            holder.promoListBinding.handler = PromoHandler(listener, position)
    }

    class PromoViewHolder(activityPromoListBinding: ActivityPromoListBinding) : RecyclerView.ViewHolder(activityPromoListBinding.getRoot()) {
        val promoListBinding: ActivityPromoListBinding

        init {
            promoListBinding = activityPromoListBinding
        }
    }

    fun addData(promo: Promo) {
        data?.add(0,promo)
        notifyDataSetChanged()
    }

    fun removeData(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }

    fun updateData(position: Int,promo: Promo) {
        data?.removeAt(position)
        data?.add(position,promo);
        notifyItemInserted(position)
    }

    fun getData(position: Int):Promo {
        val item = data?.get(position)
        return item!!
    }
}