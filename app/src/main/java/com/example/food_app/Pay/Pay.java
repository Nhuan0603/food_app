package com.example.food_app.Pay;


import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.food_app.R;
import com.example.food_app.fragment.AccountFragment;
import com.example.food_app.fragment.cart.CartModel;
import com.example.food_app.fragment.cart.CartTab;
import com.example.food_app.fragment.notification.NotificationApplication;
import com.example.food_app.fragment.notification.NotificationTab;

import java.util.Date;

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
    private TextView titleMethodPay;
    private RelativeLayout relativeLayoutDieuKhoan;
    String totalPriceProduct;

    private Button btnDatHang;
    public Pay() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanh_toan);

        data();
        dataFromCartTab();
        imgFixMethodPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Method.class);
                startActivityForResult(intent, REQUEST_METHOD);
            }
        });
        imgToAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Pay, new AccountFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        relativeLayoutDieuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://docln.net/";

                // Mở đường link trong trình duyệt
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification("Bạn có "+ CartModel.cartList.size() + " đơn hàng!","Tài xế đang trên đường giao đến bạn trng vòng 10p tới");
            }
        });
    }


    private void dataFromCartTab() {
        totalPriceProduct = getIntent().getStringExtra("totalPrice");
        productPrice.setText(totalPriceProduct);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_METHOD && resultCode == RESULT_OK) {
            MethodData methodData = (MethodData) data.getExtras().get("object_MethodPay");
            titleMethodPay.setText(methodData.getTitle());
        }
    }


    public void data(){
        imgToAccount = findViewById(R.id.img_fixLocation);
        imgFixMethodPay = findViewById(R.id.ic_fixMethodPay);
        userName = findViewById(R.id.tv_userName);
        phoneNumber = findViewById(R.id.tv_userPhone);
        location = findViewById(R.id.tv_userLocation);
        productPrice = findViewById(R.id.tv_price);
        deliveryPrice = findViewById(R.id.tv_priceDelivery);
        priceTotal = findViewById(R.id.tv_priceTotal);
        distant = findViewById(R.id.tv_distant);
        totalBottomPrice = findViewById(R.id.tv_totalPriceBottom);
        titleMethodPay = findViewById(R.id.tv_titleMehtodPay);
        relativeLayoutDieuKhoan = findViewById(R.id.rl_dieuKhoan);
        btnDatHang = findViewById(R.id.btn_datHang);
    }

    public TextView getUserName() {
        return userName;
    }

    public TextView getTotalBottomPrice() {
        return totalBottomPrice;
    }

    public TextView getPhoneNumber() {
        return phoneNumber;
    }

    public TextView getLocation() {
        return location;
    }

    public TextView getDistant() {
        return distant;
    }

    public TextView getProductPrice() {
        return productPrice;
    }

    public TextView getDeliveryPrice() {
        return deliveryPrice;
    }

    public TextView getPriceTotal() {
        return priceTotal;
    }

    public String getTotalPriceProduct() {
        return totalPriceProduct;
    }


    private void sendNotification(String title, String content) {
//        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri sound = Uri.parse("android.resource://" + this.getPackageName().toString() + "/" + R.raw.iphone_sound);

//        Intent resultIntent = new Intent(this, NotificationTab.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addNextIntentWithParentStack(resultIntent);
//
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(getNotificationId(),PendingIntent.FLAG_UPDATE_CURRENT);

        Notification newMessageNotification = new NotificationCompat.Builder(this, NotificationApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle(title) //emailObject.getSenderName()
                .setContentText(content) //emailObject.getSubject()
                .setLargeIcon(bitmap) //emailObject.getSenderAvatar()
//                .setGroup(GROUP_KEY_WORK_EMAIL)
//                .setContentIntent(resultPendingIntent)
                .setSound(sound)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(getNotificationId(), newMessageNotification);
    }

    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}
