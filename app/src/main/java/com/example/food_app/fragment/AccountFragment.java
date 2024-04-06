package com.example.food_app.fragment;

import static java.lang.String.valueOf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_app.R;

public class AccountFragment extends Fragment {

    private EditText phoneNumber;
    private EditText email;
    private EditText location;
    private Button btnSave;
    View myview;
    private boolean check = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_account, container, false);

        phoneNumber = myview.findViewById(R.id.ed_phone);
        email = myview.findViewById(R.id.ed_email);
        location = myview.findViewById(R.id.ed_location);
        btnSave = myview.findViewById(R.id.btn_save_card);

//        btnSave.setOnClickListener(new View.OnClickListener() {
//
//            String phone = phoneNumber.toString();
//            String email2 = email.toString();
//            String location2 = location.toString();
//            @Override
//            public void onClick(View v) {
//                if (phone == "" || location2.equals("")){
//                    Toast.makeText(AccountFragment.this, "Please fill this field!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });


        return myview;
    }


}
