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


        rcv = myView.findViewById(R.id.rcv_notification);
        notificationAdapter = new NotificationAdapter(getContext(),this.notificationModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        rcv.setAdapter(notificationAdapter);

        return myView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
