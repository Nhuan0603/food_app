package com.example.food_app.Pay;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.food_app.R;
import com.example.food_app.fragment.AccountFragment;
import com.example.food_app.fragment.cart.CartTab;

import java.util.Random;

public class Pay extends AppCompatActivity {
    private static final int REQUEST_METHOD = 1;

    private ImageView imgFixMethodPay;
    private ImageView imgToAccount;

    CartTab cartTab;

    private TextView userName;
    private TextView totalBottomPrice;
    private TextView phoneNumber;
    private TextView location;
    private TextView distant;
    private TextView productPrice;
    private TextView deliveryPrice;
    private TextView priceTotal;
    String totalPriceProduct;

    public Pay() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanh_toan);

        data();

        totalPriceProduct = getIntent().getStringExtra("totalPrice");
        productPrice.setText(totalPriceProduct);


        imgFixMethodPay = findViewById(R.id.ic_fixMethodPay);
        imgFixMethodPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Method.class);
                startActivityForResult(intent, REQUEST_METHOD);
            }
        });

        imgToAccount = findViewById(R.id.img_fixLocation);
        imgToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Pay, new AccountFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                Intent i = new Intent(getApplication(),AccountFragment.class);
//                startActivity(i);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_METHOD && resultCode == RESULT_OK) {
            // Cập nhật lại dữ liệu ở đây (nếu cần)
        }
    }



    public void data(){
        userName = findViewById(R.id.tv_userName);
        phoneNumber = findViewById(R.id.tv_userPhone);
        location = findViewById(R.id.tv_userLocation);
        productPrice = findViewById(R.id.tv_price);
        deliveryPrice = findViewById(R.id.tv_priceDelivery);
        priceTotal = findViewById(R.id.tv_priceTotal);
        distant = findViewById(R.id.tv_distant);
        totalBottomPrice = findViewById(R.id.tv_totalPriceBottom);
    }

    public TextView getDistant() {
        return distant;
    }

    public TextView getUserName() {
        return userName;
    }

    public TextView getPhoneNumber() {
        return phoneNumber;
    }

    public TextView getLocation() {
        return location;
    }

    public TextView getProductPrice() {
        return productPrice;
    }

    public TextView getDeliveryPrice() {
        return deliveryPrice;
    }

    public String getTotalPriceProduct() {
        return totalPriceProduct;
    }

    public TextView getPriceTotal() {
        return priceTotal;
    }

    public TextView getTotalBottomPrice() {
        return totalBottomPrice;
    }
}
