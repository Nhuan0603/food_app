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
import com.example.food_app.fragment.cart.CartModel;
import com.example.food_app.fragment.food.Food;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{
    private CartModel cartModel = new CartModel();
    private NotificationModel notificationModel ;
    private Context myContext;

    public NotificationAdapter(Context myContext,NotificationModel notificationModel) {
        this.myContext = myContext;
        this.notificationModel = notificationModel;
    }
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        final Integer pid = cartModel.getFoodByOrder(position).getId();
        final Food p = cartModel.foodRepository.getFood(pid);

        holder.imgNotification.setImageURI(p.getImage());
        holder.tvTitle.setText(p.getName());
        holder.tvContent.setText("Đơn hàng đã dặt thành công và trên đường vẫn chuyển đến bạn! Chúc bạn ngon miệng!");
    }

    @Override
    public int getItemCount() {
        return CartModel.cartList.size();
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
