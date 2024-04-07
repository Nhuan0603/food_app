package com.example.food_app.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_app.Pay.Pay;
import com.example.food_app.R;

public class AccountFragment extends Fragment {
    private ImageView edit_phone;
    private ImageView edit_email;
    private ImageView edit_location;
    private TextView userEmail;
    private TextView username;
    private TextView userPhone;
    private TextView userLocation;
    private Button btnSave;
    private float randomKm = 5;
    private float gia1km = 13000;
    View myView;
    private Pay pay;

//    public AccountFragment() {
//    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_account, container, false);

        pay = (Pay) getActivity();
        btnSave = myView.findViewById(R.id.btn_save_card);
        Data();

        edit_phone = myView.findViewById(R.id.ic_edit_phone);
        edit_email = myView.findViewById(R.id.ic_edit_email);
        edit_location = myView.findViewById(R.id.ic_edit_location);
        edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPhone.setEnabled(true);
            }
        });
        edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail.setEnabled(true);
            }
        });
        edit_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocation.setEnabled(true);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToPay();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Thong bao").setMessage("Da luu thanh cong").setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return myView;
    }

    private void Data() {
        username = myView.findViewById(R.id.name_user_account);
        userEmail = myView.findViewById(R.id.ed_email);
        userPhone = myView.findViewById(R.id.ed_phone);
        userLocation = myView.findViewById(R.id.ed_location);
    }

    public float soTienGiaoHang() { //bang so tien 1 km * so km
        float giaGiaoHang;
        try {
            giaGiaoHang = randomKm * gia1km;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return giaGiaoHang;
    }
    private float TongTien() {
        float sum;
        try {
            sum = Float.parseFloat(pay.getTotalPriceProduct()) + soTienGiaoHang();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    private void sendDataToPay() {
        String name = username.getText().toString().trim();
        pay.getUserName().setText(name);
        String phone = userPhone.getText().toString().trim();
        pay.getPhoneNumber().setText(phone);
        String locate = userLocation.getText().toString().trim();
        pay.getLocation().setText(locate);
        pay.getDistant().setText("" + randomKm);

        float deliveryPrice = soTienGiaoHang();
        pay.getDeliveryPrice().setText(String.valueOf(deliveryPrice));

        float totalPrice = TongTien();
        pay.getPriceTotal().setText(String.valueOf(totalPrice));
        pay.getTotalBottomPrice().setText(String.valueOf(totalPrice));
    }

}
