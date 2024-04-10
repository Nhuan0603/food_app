package com.example.food_app.fragment.cart;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.MainActivity;
import com.example.food_app.Pay.Pay;
import com.example.food_app.R;
import com.example.food_app.fragment.history.HistoryModel;
import com.example.food_app.fragment.notification.NotificationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CartTab extends Fragment implements CartAdapter.CartAdapterListener {
    private View myView;
    private MainActivity mainActivity;
    private RecyclerView rvCart;
    private TextView tvTotal;
    private Button btnThanhToan;
    private CartModel cartModel = new CartModel();
    private NotificationModel notificationModel = new NotificationModel();
    private HistoryModel historyModel = new HistoryModel();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab_cart, container, false);

        CartAdapter cartAdapter = new CartAdapter(getContext(), this.cartModel, this);

        rvCart = myView.findViewById(R.id.rvCart);
        tvTotal = myView.findViewById(R.id.tv_total);

        mainActivity = (MainActivity) getContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);

        rvCart.setAdapter(cartAdapter);
        tvTotal.setText("" + this.cartModel.getTotalPrice());

        btnThanhToan = myView.findViewById(R.id.btn_thanhToan);

        sentdataToPay();
        return myView;
    }

    public void sentdataToPay(){
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationModel.addNameFood(cartModel);

                long currentTimeMillis = System.currentTimeMillis();
                // Chuyển đổi thời gian thành định dạng ngày tháng
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formattedDate = sdf.format(new Date(currentTimeMillis));
                historyModel.addNameFoodToHistory(cartModel, formattedDate);

                Intent intent = new Intent(getActivity(), Pay.class);
                intent.putExtra("totalPrice", tvTotal.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
    @Override
    public void onUpdateData() {
        tvTotal.setText("" + this.cartModel.getTotalPrice());
    }

}
