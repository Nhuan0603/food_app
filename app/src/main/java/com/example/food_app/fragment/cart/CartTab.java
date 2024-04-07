package com.example.food_app.fragment.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.MainActivity;
import com.example.food_app.Pay.Pay;
import com.example.food_app.R;


public class CartTab extends Fragment {
    private View myView;
    private MainActivity mainActivity;
    private RecyclerView rvCart;
    private TextView tvTotal;
    private Button btnThanhToan;
    private Pay mPay;
    private CartModel cart = new CartModel();
    private Context mContext;

    public CartTab(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.tab_cart, container, false);

        rvCart = myView.findViewById(R.id.rvCart);
        tvTotal = myView.findViewById(R.id.tv_total);

        mainActivity = (MainActivity) getContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(layoutManager);

        CartAdapter cartAdapter =new CartAdapter(getContext(), this.cart);
        rvCart.setAdapter(cartAdapter);
        tvTotal.setText("" + this.cart.getTotalPrice());

        btnThanhToan = myView.findViewById(R.id.btn_thanhToan);

        sentdataToPay();
        return myView;
    }

    public void updateData(){
        tvTotal.setText("" + this.cart.getTotalPrice());
    }
    public void sentdataToPay(){
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Pay.class);
                intent.putExtra("totalPrice", tvTotal.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

}
