package com.worklight.common.security;


import android.content.Context;

/**
 * Created by zt on 16/7/18.
 */
public class AppAuthenticityToken {
    static {
        System.loadLibrary("authjni");
    }

    public static native String a1(Context arg1,String parmString);
}
