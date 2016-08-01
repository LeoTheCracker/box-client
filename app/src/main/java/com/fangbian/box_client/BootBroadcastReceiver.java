package com.fangbian.box_client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BootBroadcastReceiver extends BroadcastReceiver {
    public BootBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent service = new Intent(context, SocketClientService.class);
//        context.startService(service);
//        Log.v("TAG", "Service started.");
    }
}
