package com.fangbian.box_client;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;
import jd.wjlogin_sdk.util.DecryptorJni;

/**
 * Created by zt on 16/11/21.
 */

public class JdDecryptorTest extends ApplicationTestCase<Application> {

    private static String TAG = "JdDecryptorTest";

    public JdDecryptorTest() {
        super(Application.class);
    }

    public void Test1(){
        Log.d(TAG,DecryptorJni.jniRandomKey());
    }
}
