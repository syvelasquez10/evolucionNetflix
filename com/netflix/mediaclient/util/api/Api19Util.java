// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.api;

import android.annotation.TargetApi;

@TargetApi(19)
public class Api19Util
{
    public static boolean isLowRamDevice() {
        return isLowRamDeviceStatic();
    }
    
    private static boolean isLowRamDeviceStatic() {
        return "true".equals(System.getProperty("ro.config.low_ram", "false"));
    }
}
