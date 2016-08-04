package com.fangbian.box_client;

import android.app.Service;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.IBinder;

import com.MobileTicket.CheckCodeUtil;
import com.worklight.common.security.AppAuthenticityToken;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClientService extends Service {
    private static String TAG = "SocketClientService";
    private static Socket socket = null;
    private final static Logger logger = LoggerFactory.getLogger(MainActivity.class);

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
        logger.info("Service Stopping...");
        return super.stopService(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //upgradeRootPermission(getPackageCodePath());


        if (socket != null) {
            logger.info("Service is running...");
            return super.onStartCommand(intent, flags, startId);
        }

//        Intent _intent = new Intent("com.fangbian.zt.SERVICE");
//        boolean result = bindService(_intent, setDeviceInfoByPhone_Connection, Context.BIND_AUTO_CREATE);
//        if (!result) {
//            message = "Service Connect failed!";
//            logger.error(message);
//            invokeCallback(true, message);
//        }

        logger.info("Service Starting...");
        try {
            socket = IO.socket("http://120.26.213.143:8001/terminal");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    logger.info("CONNECTED");
//                    socket.emit("data", "hi server");
                }
            }).on("d_checkcode", new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    String checkCode = "";
                    String error = "";
                    try {
                        checkCode = CheckCodeUtil.decheckcode("", String.valueOf(args[1]));
                    } catch (Exception ex) {
                        error = ex.getMessage();
                    }
                    //socket.emit("d_checkcode",checkCode);
                    try {
                        socket.emit("checkcode",
                                new ResultMessage<String>(String.valueOf(args[0]), checkCode, error,
                                        System.currentTimeMillis(), getLocalIpAddress()).getJsonObject());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }).on("e_checkcode", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String checkCode = "";
                    String error = "";
                    try {
                        checkCode = CheckCodeUtil.checkcode("", String.valueOf(args[1]));
                    } catch (Exception ex) {
                        error = ex.getMessage();
                        logger.error(error);
                    }

                    //socket.emit("e_checkcode",checkCode);
                    try {
                        socket.emit("checkcode",
                                new ResultMessage<String>(String.valueOf(args[0]), checkCode, error,
                                        System.currentTimeMillis(), getLocalIpAddress()).getJsonObject());
                    } catch (JSONException e) {
                        logger.error(e.getMessage());
                    }
                }
            }).on("realm", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    ZTContext tokenContext = new ZTContext();
                    ZTPackageInfo localSuanyaPackageInfo = new ZTPackageInfo();
                    Signature[] arrayOfSignature = new Signature[1];
                    arrayOfSignature[0] = new Signature("30820237308201a0a0030201020204520c9714300d06092a864886f70d0101050500305f310b30090603550406130230313110300e060355040813076265696a696e673110300e060355040713076265696a696e67310c300a060355040a13037a7463310e300c060355040b13057261696c73310e300c0603550403130531323330363020170d3133303831353038353334305a180f32313133303732323038353334305a305f310b30090603550406130230313110300e060355040813076265696a696e673110300e060355040713076265696a696e67310c300a060355040a13037a7463310e300c060355040b13057261696c73310e300c06035504031305313233303630819f300d06092a864886f70d010101050003818d00308189028181009fea9d56277fc27c68a3836173d286791af2be38e7384afce32f38b164d83a6c6ec7656f7881c444c2e677e2195415a92bb6a06638886d132d26ce47895fa96076085813ee7b264d6017b21a64c75ae4ba63496906fe77fda68305d8ee426ece06e1b683bf78eccedf8bcd9817376d26c50ffb745f378a50834fd6522db3a6150203010001300d06092a864886f70d0101050500038181003e646d1dea5763f12008d36023a0812bc4452b15d3f8cbf189e2f6b43b89a373e4cc4ec7197f31e9c765821d6c8499cd0a71e49fac114b0b90bf2db8f7520d5ab922c04b602f7e81cd7f4dcb9e94118691bf2dd6277bc404bdfc6906fab2145c3426a0624a549cd85e83fe4822e686abc4119f21dbddb8cb71c5ebdb5f042688");
                    localSuanyaPackageInfo.setSignatures(arrayOfSignature);
                    ZtPackageManager localSuanyaPackageManager = new ZtPackageManager();
                    localSuanyaPackageManager.setPackageInfo(localSuanyaPackageInfo);
                    tokenContext.setPackageManager(localSuanyaPackageManager);
                    tokenContext.setPackageName("com.MobileTicket");
                    String realm="";
                    String error = "";
                    try {
                        realm = AppAuthenticityToken.a1(tokenContext, String.valueOf(args[1]));
                    }
                    catch (Exception e){
                        error = e.getMessage();
                        logger.error(error);
                    }

                    try {
                        socket.emit("realm",
                                new ResultMessage(String.valueOf(args[0]), realm, error,
                                        System.currentTimeMillis(), getLocalIpAddress()).getJsonObject());
                    } catch (JSONException e) {
                        logger.error(e.getMessage());
                    }
                }
            }).on("control", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    logger.info(String.valueOf(args[0]));
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    logger.warn("DISCONNECT");
                }
            }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    logger.error("EVENT_CONNECT_ERROR:" + args[0].toString());
                }
            }).on(Socket.EVENT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    logger.error("EVENT_ERROR:" + args[0].toString());
                }
            });

            socket.connect();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage() == null ? "get local ip address faild" : ex.getMessage());
        }
        return "";
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
            logger.error("请求root权限失败\n" + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                logger.error("请求root权限失败\n" + e.getMessage());
            }
        }
        return true;
    }
}
