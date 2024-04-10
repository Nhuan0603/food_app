package com.example.food_app.fragment.notification;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;
import com.example.food_app.fragment.cart.CartModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationTab extends Fragment {
    private Button btnHome;
    private RecyclerView rcv;
    private NotificationAdapter  notificationAdapter;
    private NotificationModel notificationModel = new NotificationModel();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.tab_notification, container, false);

        btnHome = myView.findViewById(R.id.btn_home);

        Button btnNotification = myView.findViewById(R.id.btn_notification);

        btnNotification.setOnClickListener(v -> {
//            Log.d("fghjgggggggggggggggggggggg", String.valueOf();
            sendNotification("Bạn có "+ CartModel.cartList.size() + " đơn hàng!","Tài xế đang trên đường giao đến bạn trng vòng 10p tới");
//
        });

        rcv = myView.findViewById(R.id.rcv_notification);
        notificationAdapter = new NotificationAdapter(getContext(),this.notificationModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        rcv.setAdapter(notificationAdapter);

        return myView;
    }


    private void sendNotification(String title, String content) {
//        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri sound = Uri.parse("android.resource://" + getContext().getPackageName().toString() + "/" + R.raw.iphone_sound);

        Notification newMessageNotification = new NotificationCompat.Builder(getContext(), NotificationApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_email)
                .setContentTitle(title) //emailObject.getSenderName()
                .setContentText(content) //emailObject.getSubject()
                .setLargeIcon(bitmap) //emailObject.getSenderAvatar()
//                .setGroup(GROUP_KEY_WORK_EMAIL)
                .setSound(sound)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
