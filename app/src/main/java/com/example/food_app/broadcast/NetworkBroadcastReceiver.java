package com.example.food_app.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.widget.Toast;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if (isNetWorkAvailable(context)){
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Internet Disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean isNetWorkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)    return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            Network network = connectivityManager.getActiveNetwork();
            if(network ==  null)    return false;

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);

        }else{
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}