package com.MobileTicket;

/**
 * Created by zt on 16/7/11.
 */
public class CheckCodeUtil {
    static {
        System.loadLibrary("checkcode");
    }

    public CheckCodeUtil() {
        super();
    }

    public static native String checkcode( String arg1, String arg2);
    public static native String decheckcode( String arg1, String arg2);
}
