package com.example.food_app.fragment.notification;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeTab extends Fragment {

    public int i=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.tab_notice, container, false);

        Button btnNotification = myView.findViewById(R.id.btn_notification);
        btnNotification.setOnClickListener(v -> {
            sendNotification(getListNnotification().get(i).getTitle(),getListNnotification().get(i).getContent());
            i=i+1;
            if (i == getListNnotification().size()) i=0;
        });

        RecyclerView rcv = myView.findViewById(R.id.rcv_notification);
        NotificationAdapter notificationAdapter = new NotificationAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        notificationAdapter.setData(getListNnotification());
        rcv.setAdapter(notificationAdapter);

        return myView;
    }

    private List<Notification_item> getListNnotification() {
        List<Notification_item> list = new ArrayList<>();

        list.add(new Notification_item(R.drawable.ff_2,"no_1","Notification Food"));
        list.add(new Notification_item(R.drawable.ff_3,"no_2","Notification Food"));
        list.add(new Notification_item(R.drawable.ff_4,"no_3","Notification Food"));
        list.add(new Notification_item(R.drawable.ff_5,"no_4","Notification Food"));
        return list;
    }

    private void sendNotification(String title, String content) {
//        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri sound = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.iphone_sound);

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
