package com.fangbian.box_client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.DataOutputStream;
import java.net.URISyntaxException;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static io.socket.client.IO.*;

public class SocketClientService extends Service {
    private static String TAG = "SocketClientService";
    private static Socket socket = null;

    public SocketClientService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service Destroing...");
        super.onDestroy();
    }

    @Override
    public boolean stopService(Intent name) {
        Log.d(TAG, "Service Stopping...");
        return super.stopService(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //upgradeRootPermission(getPackageCodePath());

        Log.d(TAG, "Service Starting...");
        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            opts.reconnection = false;
            socket = IO.socket("http://localhost",opts);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "CONNECTED");
                    socket.emit("data", "hi server");
//                    socket.disconnect();
                }
            }).on("d_checkcode", new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                }

            }).on("e_checkcode", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
//                    Ack ack = (Ack) args[args.length - 1];
//                    ack.call();
                }

            }).on("control", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, String.valueOf(args[0]));
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "DISCONNECT");
                }
            });

            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Log.e(TAG, "请求root权限失败\n" + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                Log.e(TAG, "请求root权限失败\n" + e.getMessage());
            }
        }
        return true;
    }
}
