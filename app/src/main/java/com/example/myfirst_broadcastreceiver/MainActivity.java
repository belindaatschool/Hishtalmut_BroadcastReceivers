package com.example.myfirst_broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private WifiReceiver wifiReceiver;
    private boolean isRegistered = false;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();//hhhh hahaha
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver, filter);
        isRegistered = true;
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(isRegistered)
        {
            unregisterReceiver(wifiReceiver);
            isRegistered=false;
        }
    }
}