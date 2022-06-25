package com.example.myfirst_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean online = isWifiActive(context, intent);
//        boolean online = isOnline(context);
        Toast.makeText(context, "is online? -> " + online, Toast.LENGTH_SHORT).show();
    }
    public boolean isWifiActive(Context context, Intent intent){
        boolean resalt = false;
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);

        switch (wifiStateExtra) {
            case WifiManager.WIFI_STATE_ENABLED:
                resalt = true;
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                resalt = false;
                break;
        }
        return resalt;
    }
    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
        }
}