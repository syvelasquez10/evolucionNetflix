// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.javabridge.transport.NativeTransport;

public class Time
{
    public static final String TAG = "TimeNRDP";
    
    public static long mono() {
        return NativeTransport.getNativeTimeMono();
    }
    
    public static long now() {
        return NativeTransport.getNativeTimeNow();
    }
}
