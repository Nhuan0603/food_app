package com.example.food_app.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_app.R;

public class SettingFragment extends Fragment {
    private TextView tvNightMode;
    private Switch switchNightMode;
    private TextView tvTextSize;
    private SeekBar seekBarTextSize;
    private TextView tvSampleText;
    private Button btnSaveSettings;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean nightMode;
    View myView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_setting, container, false);
        Data();
        return myView;
    }

    private void Data() {
        tvNightMode = myView.findViewById(R.id.tv_night_mode);
        switchNightMode = myView.findViewById(R.id.switch_night_mode);
        tvTextSize = myView.findViewById(R.id.tv_text_size);
        seekBarTextSize = myView.findViewById(R.id.seek_bar_text_size);
        tvSampleText = myView.findViewById(R.id.tv_sample_text);
        btnSaveSettings = myView.findViewById(R.id.btn_save_settings);
    }
}
