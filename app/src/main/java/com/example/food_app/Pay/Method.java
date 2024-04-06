package com.example.food_app.Pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.R;

public class Method extends AppCompatActivity {
    private ImageView imgBackToPay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_pay);

        imgBackToPay = findViewById(R.id.img_backToPay);
        imgBackToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pay.class);
                startActivity(intent);
            }
        });
    }
}
