// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.soloader;

public class SoLoaderShim
{
    private static volatile SoLoaderShim$Handler sHandler;
    
    static {
        SoLoaderShim.sHandler = new SoLoaderShim$DefaultHandler();
    }
    
    public static void loadLibrary(final String s) {
        SoLoaderShim.sHandler.loadLibrary(s);
    }
    
    public static void setHandler(final SoLoaderShim$Handler sHandler) {
        if (sHandler == null) {
            throw new NullPointerException("Handler cannot be null");
        }
        SoLoaderShim.sHandler = sHandler;
    }
}
