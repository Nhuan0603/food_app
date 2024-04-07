package com.example.food_app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryLowBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
//
//        }
        int level = (int) intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        Toast.makeText(context, "Battery level low: " + level, Toast.LENGTH_SHORT).show();
    }
}
