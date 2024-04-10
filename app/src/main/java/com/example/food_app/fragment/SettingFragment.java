package com.example.food_app.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.food_app.R;

public class SettingFragment extends Fragment {

    View mView;
    private Switch mSwitch;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_setting, container, false);

        mSwitch = mView.findViewById(R.id.switch23);
        sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode  = sharedPreferences.getBoolean("night", false);
        if(nightMode){
            mSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });
        return mView;
    }
}
