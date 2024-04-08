package com.example.food_app.Pay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.R;
import com.example.food_app.fragment.cart.CartTab;

public class Method extends AppCompatActivity {
    private ImageView imgBackToPay;
    private RelativeLayout rlBank;
    private RelativeLayout rlCard;
    private RelativeLayout rlCash;
    private ImageView imgBank;
    private ImageView imgCash;
    private ImageView imgCard;
    private TextView tvBank;
    private TextView tvCard;
    private TextView tvCash;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_pay);

        rlBank =(RelativeLayout) findViewById (R.id.rl_Bank);
        rlCard = (RelativeLayout) findViewById(R.id.rl_Card);
        rlCash = (RelativeLayout) findViewById(R.id.rl_cash);
        imgBank = (ImageView) findViewById(R.id.img_bank);
        imgCard = (ImageView) findViewById(R.id.img_cardBank);
        imgCash = (ImageView) findViewById(R.id.img_cash);
        tvCard = (TextView) findViewById(R.id.tv_card);
        tvBank = (TextView) findViewById(R.id.tv_bank);
        tvCash = (TextView) findViewById(R.id.tv_cash);
        if (rlBank.isSelected()){
            String bankText = tvCash.getText().toString().trim();
            Intent i = new Intent();
            i.putExtra("bank", bankText);
            setResult(RESULT_OK, i);
            finish();
        }
        imgBackToPay = findViewById(R.id.img_backToPay);
        imgBackToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
