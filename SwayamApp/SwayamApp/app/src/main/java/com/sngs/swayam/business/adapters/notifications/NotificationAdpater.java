package com.sngs.swayam.business.adapters.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.sngs.swayam.business.R;
import com.sngs.swayam.business.network.model.Notification.Notification;

import java.util.List;


public class NotificationAdpater extends RecyclerView.Adapter<NotificationAdpater.MyViewHolder> {

    List<Notification> arrayList;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView notification_history_title_txt,notification_history_message_txt;

        public MyViewHolder(View view) {
            super(view);

            notification_history_title_txt = (TextView) view.findViewById(R.id.tvTitle);
            notification_history_message_txt = (TextView) view.findViewById(R.id.tvSubTitle);
        }
    }


    public NotificationAdpater(Context context_app, List<Notification> arrayList) {
        this.context = context_app;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.notification_history_title_txt.setText(""+arrayList.get(position).getNotiType());
        holder.notification_history_message_txt.setText(""+arrayList.get(position).getNotiMessage());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}

