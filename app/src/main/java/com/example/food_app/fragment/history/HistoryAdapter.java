package com.example.food_app.fragment.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.fragment.cart.CartModel;
import com.example.food_app.fragment.food.Food;
import com.example.food_app.fragment.notification.NotificationAdapter;
import com.example.food_app.fragment.notification.NotificationModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private CartModel cartModel = new CartModel();
    private HistoryModel historyModel ;
    private Context myContext;

    public HistoryAdapter(HistoryModel historyModel, Context myContext) {
        this.historyModel = historyModel;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        final Integer pid = cartModel.getFoodByOrder(position).getId();
        final Food p = cartModel.foodRepository.getFood(pid);

        holder.imgHistory.setImageURI(p.getImage());
        holder.tvNameHistory.setText(p.getName());
        holder.tvDateHistory.setText(historyModel.DateHistory);
        holder.btn_reBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReBuy();
            }
        });
    }

    private void ReBuy() {
        //quay lai cart
    }

    @Override
    public int getItemCount() {
        return CartModel.cartList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgHistory;
        private TextView tvNameHistory;
        private TextView tvDateHistory;
        private Button btn_reBuy;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHistory = itemView.findViewById(R.id.img_history);
            tvNameHistory = itemView.findViewById(R.id.name_history);
            tvDateHistory = itemView.findViewById(R.id.date_history);
            btn_reBuy = itemView.findViewById(R.id.re_buy_history);
        }
    }
}
