// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import com.facebook.soloader.SoLoader;

public class ReactBridge
{
    static {
        staticInit();
    }
    
    public static void staticInit() {
        SoLoader.loadLibrary("reactnativejni");
        SoLoader.loadLibrary("reactnativejnifb");
    }
}
