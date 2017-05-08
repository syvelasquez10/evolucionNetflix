// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class NetflixTransactionIdGenerator
{
    public static final String TAG = "TimeNRDP";
    
    private static long generate64Id() {
        return (new PositiveRandom().nextPositive() & 0xFFFFFFFFL) | (int)(Time.now() / 1000L) << 32;
    }
    
    public static String generateDxId() {
        return Long.toString(generate64Id());
    }
    
    public static String generateOxId() {
        return Long.toString(generate64Id());
    }
    
    public static String generateXid() {
        return Long.toString(generate64Id());
    }
}
