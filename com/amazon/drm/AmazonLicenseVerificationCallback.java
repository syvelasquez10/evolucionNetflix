// 
// Decompiled by Procyon v0.5.30
// 

package com.amazon.drm;

public class AmazonLicenseVerificationCallback
{
    private static boolean called;
    
    static {
        AmazonLicenseVerificationCallback.called = false;
    }
    
    public static boolean isCalled() {
        synchronized (AmazonLicenseVerificationCallback.class) {
            return AmazonLicenseVerificationCallback.called;
        }
    }
}
