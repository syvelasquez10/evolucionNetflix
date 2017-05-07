// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.api;

import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.Bitmap;
import android.annotation.TargetApi;

@TargetApi(19)
public class Api19Util
{
    public static int getBitmapByteCount(final Bitmap bitmap) {
        if (AndroidUtils.getAndroidVersion() < 19) {
            return bitmap.getByteCount();
        }
        return bitmap.getAllocationByteCount();
    }
    
    public static boolean isLowRamDevice() {
        return isLowRamDeviceStatic();
    }
    
    private static boolean isLowRamDeviceStatic() {
        return "true".equals(System.getProperty("ro.config.low_ram", "false"));
    }
}
