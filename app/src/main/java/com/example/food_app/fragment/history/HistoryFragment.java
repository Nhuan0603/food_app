package com.example.food_app.fragment.history;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.fragment.cart.CartModel;
import com.example.food_app.fragment.tab_home.HomeTab;

public class HistoryFragment extends Fragment {
    private RecyclerView rcv_history;
    private HistoryAdapter historyAdapter;
    private HistoryModel historyModel = new HistoryModel();
    private LinearLayout linearLayout;
    private Button btnBuyNow;

    @Override
    public void onStart() {
        super.onStart();
        if(CartModel.cartList.size()!= 0){
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_history, container, false);

        rcv_history = myView.findViewById(R.id.rcv_history);

        linearLayout = myView.findViewById(R.id.Buy_if_null);
        btnBuyNow = myView.findViewById(R.id.btn_buy_if_null);
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,new HomeTab());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        historyAdapter = new HistoryAdapter(this.historyModel,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcv_history.setLayoutManager(linearLayoutManager);
        rcv_history.setAdapter(historyAdapter);
        return myView;
    }
}
