package com.example.food_app.broadcast;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.food_app.R;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    Button btnReTry;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if (!isNetWorkAvailable(context)){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View layout_dialog = LayoutInflater.from(context).inflate(R.layout.dialog_network,null);
                builder.setView(layout_dialog);

                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.getWindow().setGravity(Gravity.CENTER);

                btnReTry = layout_dialog.findViewById(R.id.btn_reTry);
                btnReTry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isNetWorkAvailable(context)){
                            dialog.dismiss();
                            Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
//            else {
//                Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
//            }
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
