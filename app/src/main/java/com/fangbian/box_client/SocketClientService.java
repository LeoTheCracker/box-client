package com.fangbian.box_client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.MobileTicket.CheckCodeUtil;

import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;

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
//        Log.d(TAG, "Service Destroing...");
//        socket.disconnect();
//        socket = null;
        super.onDestroy();
    }

    @Override
    public boolean stopService(Intent name) {
        Log.d(TAG, "Service Stopping...");
        return super.stopService(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        upgradeRootPermission(getPackageCodePath());

        if (socket != null) {
            Log.d(TAG, "Service is running...");
            return super.onStartCommand(intent, flags, startId);
        }

        Log.d(TAG, "Service Starting...");
        try {
            IO.Options opt = new IO.Options();
            opt.timestampParam="date="+String.valueOf(System.currentTimeMillis());
            opt.timestampRequests=true;
            socket = IO.socket("http://192.168.1.13:8000/terminal",opt);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "CONNECTED");
//                    socket.emit("data", "hi server");
                }
            }).on("d_checkcode", new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    String checkCode = "";
                    try {
                        checkCode = CheckCodeUtil.decheckcode("", String.valueOf(args[0]));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    socket.emit("d_checkcode", checkCode);
                }

            }).on("e_checkcode", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String checkCode = "";
                    try {
                        checkCode = CheckCodeUtil.checkcode("", String.valueOf(args[0]));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    socket.emit("e_checkcode", checkCode);
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
            }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "EVENT_CONNECT_ERROR:" + args[0].toString());
                }
            }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.d(TAG, "EVENT_ERROR:" + args[0].toString());
                }
            });

            socket.connect();

//            socket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    Transport transport = (Transport) args[0];
//
//                    transport.on(Transport.EVENT_REQUEST_HEADERS, new Emitter.Listener() {
//                        @Override
//                        public void call(Object... args) {
//                            @SuppressWarnings("unchecked")
//                            Map<String, List<String>> headers = (Map<String, List<String>>) args[0];
//                            headers.put("Date", Arrays.asList(String.valueOf(System.currentTimeMillis())));
//                        }
//                    });
//
////                    transport.on(Transport.EVENT_RESPONSE_HEADERS, new Emitter.Listener() {
////                        @Override
////                        public void call(Object... args) {
////                            @SuppressWarnings("unchecked")
//////                            Map<String, List<String>> headers = (Map<String, List<String>>)args[0];
////                                    // access response headers
//////                            String cookie = headers.get("Set-Cookie").get(0);
////                                    Map<String, List<String>> headers = (Map<String, List<String>>) args[0];
////                            headers.put("Cookie", Arrays.asList(String.valueOf(System.currentTimeMillis())));
////                        }
////                    });
//                }
//            });
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
