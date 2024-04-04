package com.example.food_app.fragment.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{
    private Context myContext;
    private List<Notification_item> myListNotification;

    public NotificationAdapter(Context myContext) {
        this.myContext = myContext;
    }
    public void setData(List<Notification_item> list){
        this.myListNotification = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
       Notification_item notification = myListNotification.get(position);
        if (notification == null)
            return;
        holder.imgNotification.setImageResource(notification.getImgResourceID());
        holder.tvTitle.setText(notification.getTitle());
        holder.tvContent.setText(notification.getContent());
    }

    @Override
    public int getItemCount() {
        return myListNotification.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgNotification;
        private TextView tvTitle;
        private TextView tvContent;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNotification = itemView.findViewById(R.id.img_notification);
            tvTitle = itemView.findViewById(R.id.title_notificatoin);
            tvContent = itemView.findViewById(R.id.content_notification);
        }
    }
}
