package com.example.food_app.fragment.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.MainActivity;
import com.example.food_app.R;
import com.example.food_app.databinding.ActivityMainBinding;


public class CartTab extends Fragment {
    private View myView;
    private AppBarConfiguration appBarConfiguration;

    private MainActivity mainActivity;
    private RecyclerView rvCart;
    private TextView tvTotal;
    private CartModel cart = new CartModel();
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

        return myView;
    }
    public void updateData(){

        tvTotal.setText("" + this.cart.getTotalPrice());

    }

}
