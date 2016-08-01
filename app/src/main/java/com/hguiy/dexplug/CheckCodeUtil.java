package com.hguiy.dexplug;

/**
 * Created by zt on 16/8/1.
 */
import android.util.Log;

/**
 * Created by chenjava on 2016/3/24.
 */
public class CheckCodeUtil {
    private final static String LOG_TAG = "INJECT_Unpack";
    static {
        Log.d(LOG_TAG, "CheckCodeUtil static init");
        System.load("/data/data/com.hguiy.dextool/cache/libdexInject.so");
    }
    public static  native String checkcode( String arg1, String arg3);
    public static  native String decheckcode( String arg1,String arg2);
}