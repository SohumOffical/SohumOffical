package com.sngs.swayam.business.adapters.onboarding

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sngs.swayam.business.R
import com.sngs.swayam.business.activity.onboarding.promos.OfferDetailActivity
import com.sngs.swayam.business.network.model.PromoDetail.PromotionListResult
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.my_promotion_item_layout.view.*

class MyPromotionListAdapter  (private var arrayList: List<PromotionListResult>, private val context: Context) :
    RecyclerView.Adapter<MyPromotionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_promotion_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(arrayList.get(position).promotionAttachment)
                .placeholder(R.drawable.ic_new_user).into(holder.itemView.shop_img);

        holder.itemView.pro_shop_name_txt.setText(""+arrayList.get(position).promotionTitle)

        holder.itemView.pro_shop_detail_txt.setText(""+arrayList.get(position).promotionDescription)
        holder.itemView.pro_date_txt.setText(""+arrayList.get(position).promotionStartDate)

        holder.itemView.prom_likes_txt.setText(""+context.resources.getString(R.string.likes)+" "+
                arrayList.get(position).getmTotalLike())
        holder.itemView.prom_dislikes_txt.setText(""+context.resources.getString(R.string.dislikes)+" "
                +arrayList.get(position).getmTotalDeslike())
        holder.itemView.viwer_count_txt.setText(""+context.resources.getString(R.string.views)+" "
                +arrayList.get(position).getmTotalViewed())

        holder.itemView.view_more_rel.setOnClickListener {
            val intent = Intent(context, OfferDetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("Selected_pos",""+position)
            context.startActivity(intent)
        }

    }

}