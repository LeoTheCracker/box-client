package com.fangbian.box_client;

import android.content.pm.PackageInfo;
import android.content.pm.Signature;

/**
 * Created by zt on 16/7/20.
 */
public class ZTPackageInfo extends PackageInfo
{
    public void setSignatures(Signature[] paramArrayOfSignature)
    {
        this.signatures = paramArrayOfSignature;
    }
}
