// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.systeminfo;

import android.os.Build;

public class AndroidInfoHelpers
{
    public static String getServerHost() {
        if (isRunningOnGenymotion()) {
            return "10.0.3.2:8081";
        }
        if (isRunningOnStockEmulator()) {
            return "10.0.2.2:8081";
        }
        return "localhost:8081";
    }
    
    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }
    
    private static boolean isRunningOnStockEmulator() {
        return Build.FINGERPRINT.contains("generic");
    }
}
