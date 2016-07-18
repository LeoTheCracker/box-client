package com.worklight.common.security;


import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zt on 16/7/18.
 */
public class AppAuthenticityToken {
    static {
        System.loadLibrary("authjni");
    }

    public static native String a1(AppCompatActivity arg1,String arg2);
}
