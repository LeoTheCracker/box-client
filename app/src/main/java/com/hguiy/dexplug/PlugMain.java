package com.hguiy.dexplug;

/**
 * Created by zt on 16/8/1.
 */

import android.app.Activity;
import android.content.pm.Signature;
import android.util.Log;

import com.fangbian.box_client.ResultMessage;
import com.fangbian.box_client.ZTContext;
import com.fangbian.box_client.ZTPackageInfo;
import com.fangbian.box_client.ZtPackageManager;
import com.worklight.common.security.AppAuthenticityToken;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class PlugMain {
    private static final String TAG = "PlugMain";
    public static String mSoPath = null;
    private Activity mActivity;
    private static Socket socket = null;
    private final static Logger logger = LoggerFactory.getLogger(PlugMain.class);
    private static String ip = getLocalIpAddress();

    private final static String LOG_TAG = "INJECT_Unpack";

    public PlugMain(Activity paramActivity) {
        mActivity = paramActivity;
    }

    public static String readFile(String fileName) {
        String res="";
        try{
            File file = new File(fileName);
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
            //res = EncodingUtils.getString(buffer, "UTF-8");
            res=new String(buffer,0,buffer.length,"UTF-8");
            fin.close();
        }
        catch(Exception e){
            logger.error("read file faild",e);
        }
        return res;
    }

    public static void init(int cookie, String packageName, String className) {
        Log.e(LOG_TAG, "dex plug init load!");
//        new CheckCodeUtil();

//        new Thread(new Runnable() {
//            public void run() {
//                try{
//                    Thread.sleep(800);
//                    Log.e(LOG_TAG, "init sleep !");
//                }catch (InterruptedException e){}
//
//                int nCount = 0;
//                while(true){
//                    try {
//                        String checkRet = CheckCodeUtil.checkcode("","[{20160718HH0031Aimei860842024493912}]");
////                        checkRet = null;
////                        Log.e(LOG_TAG,checkRet);
//                        Log.e(LOG_TAG,checkRet);
//                        nCount = nCount + 1;
//                        Log.e(LOG_TAG,"count:"+nCount);
//                        Thread.sleep(200); //暂停，每一秒输出一次
//                    }catch (InterruptedException e){}
//                }
//            }
//        }).start();
//        Log.e(LOG_TAG, "dex plug init end!");

        String host = readFile("/sdcard/server");
        if(host.length() == 0) {
            logger.info("host is empty.");
            return;
        }

        logger.info("host is %s.",host);

        if (socket != null) {
            logger.info("Service is running...");
            return;
        }

        logger.info("Service Starting...");

        try {
            //socket = IO.socket("http://120.26.213.143:8000/terminal");
            socket = IO.socket(String.format("http://%s/terminal",host));
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    logger.info("CONNECTED");
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
                    try {
                        socket.emit("checkcode",
                                new ResultMessage(String.valueOf(args[0]), checkCode, error,
                                        System.currentTimeMillis(), ip).getJsonObject());
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

                    try {
                        socket.emit("checkcode",
                                new ResultMessage(String.valueOf(args[0]), checkCode, error,
                                        System.currentTimeMillis(), ip).getJsonObject());
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
                    String realm = "";
                    String error = "";
                    try {
                        realm = AppAuthenticityToken.a1(tokenContext, String.valueOf(args[1]));
                    } catch (Exception e) {
                        error = e.getMessage();
                        logger.error(error);
                    }

                    try {
                        socket.emit("realm",
                                new ResultMessage(String.valueOf(args[0]), realm, error,
                                        System.currentTimeMillis(), ip).getJsonObject());
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

        return;
    }

    public static String getLocalIpAddress() {
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
}
