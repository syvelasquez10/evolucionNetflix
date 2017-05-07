// 
// Decompiled by Procyon v0.5.30
// 

package com.amazon.drm;

import com.netflix.mediaclient.Log;

public class AmazonLicenseVerificationCallback
{
    private static final String TAG = "amazon";
    private static boolean called;
    
    static {
        AmazonLicenseVerificationCallback.called = false;
    }
    
    public static boolean isCalled() {
        synchronized (AmazonLicenseVerificationCallback.class) {
            return AmazonLicenseVerificationCallback.called;
        }
    }
    
    public static void onDRMSuccess() {
        synchronized (AmazonLicenseVerificationCallback.class) {
            Log.v("amazon", "onDRMSuccess called!");
            AmazonLicenseVerificationCallback.called = true;
        }
    }
}
