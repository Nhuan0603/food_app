package com.example.food_app.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.example.food_app.R;

public class ContactFragment extends Fragment {
    private TextView tvPhoneCall;
    private TextView tvLocation;
    private TextView edThu;
    private TextView tvEmail;
    private Button btn_gui;
    private View view;
    private static final int REQUEST_CALL_PHONE = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact, container, false);

        Data();
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = tvLocation.getText().toString();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://accounts.google.com/v3/signin/identifier?continue=http%3A%2F%2Fsupport.google.com%2Fmail%2Fanswer%2F8494%3Fhl%3Dvi%26co%3DGENIE.Platform%253DDesktop&ec=GAZAdQ&hl=vi&ifkv=ARZ0qKKY3QNdsMtdkoI6BLTNXq3jU65UMD6Yi-Nx-3sku_5ft9-pxikOwXqjdO7UB6SbIOI59coNpg&passive=true&sjid=11119608599090844750-AP&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-687062196%3A1712729001837481&theme=mn&ddm=0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        tvPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        btn_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Thong bao").setMessage("Da gui thanh cong").setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edThu.setText("");
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }

    private void Data() {
        tvPhoneCall = view.findViewById(R.id.tv_phone_call);
        tvLocation = view.findViewById(R.id.tv_location_main);
        tvEmail = view.findViewById(R.id.tv_sent_email);
        btn_gui = view.findViewById(R.id.btn_guiThu);
        edThu = view.findViewById(R.id.ed_thuGui);
    }

    private void makePhoneCall(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tvPhoneCall.getText().toString()));
        startActivity(intent);
    }
}
