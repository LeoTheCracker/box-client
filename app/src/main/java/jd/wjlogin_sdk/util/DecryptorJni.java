package jd.wjlogin_sdk.util;

import android.content.Context;

/**
 * Created by zt on 16/11/21.
 */

public class DecryptorJni {

    static {
        System.loadLibrary("DecryptorJni");
    }

    public static native byte[] jniDecrypt(byte[] paramArrayOfByte, int paramInt, String paramString);

    public static native byte[] jniDecryptMsg(byte[] paramArrayOfByte, int paramInt, String paramString);

    public static native String jniDeviceKey(Context paramContext, String paramString);

    public static native byte[] jniEncrypt(byte[] paramArrayOfByte, int paramInt, String paramString);

    public static native byte[] jniEncryptMsg(byte[] paramArrayOfByte, int paramInt, String paramString);

    public static native String jniRandomKey();

    public static native String jniUserFilename(Context paramContext, String paramString);
}
