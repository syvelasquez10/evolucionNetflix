// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.os.Parcel;
import android.content.Intent;

public final class ParcelUtils
{
    private static final String NULL = "=NULL=";
    private static final String TAG;
    
    static {
        TAG = ParcelUtils.class.getSimpleName();
    }
    
    public static boolean readBoolean(final Intent intent, final String s) {
        return intent.getBooleanExtra(s, false);
    }
    
    public static boolean readBoolean(final Parcel parcel) {
        return "true".equals(parcel.readString());
    }
    
    public static float readFloat(final Parcel parcel) {
        return readFloat(parcel.toString());
    }
    
    public static float readFloat(final String s) {
        if ("=NULL=".equals(s)) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(s);
        }
        catch (Throwable t) {
            Log.e(ParcelUtils.TAG, "Failed to parse string to int ", t);
            return 0.0f;
        }
    }
    
    public static int readInt(final Intent intent, final String s) {
        return intent.getIntExtra(s, 0);
    }
    
    public static int readInt(final Parcel parcel) {
        return readInt(parcel.readString());
    }
    
    public static int readInt(final String s) {
        if ("=NULL=".equals(s)) {
            return 0;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (Throwable t) {
            Log.e(ParcelUtils.TAG, "Failed to parse string to int ", t);
            return 0;
        }
    }
    
    public static long readLong(final Intent intent, final String s) {
        return intent.getLongExtra(s, 0L);
    }
    
    public static long readLong(final Parcel parcel) {
        return readLong(parcel.readString());
    }
    
    public static long readLong(final String s) {
        if ("=NULL=".equals(s)) {
            return 0L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (Throwable t) {
            Log.e(ParcelUtils.TAG, "Failed to parse string to int ", t);
            return 0L;
        }
    }
    
    public static String readString(final Intent intent, final String s) {
        return readString(intent.getStringExtra(s));
    }
    
    public static String readString(final Parcel parcel) {
        return readString(parcel.readString());
    }
    
    public static String readString(final String s) {
        String s2 = s;
        if ("=NULL=".equals(s)) {
            s2 = null;
        }
        return s2;
    }
    
    public static String validateString(final String s) {
        if (s != null) {
            return s;
        }
        return "=NULL=";
    }
    
    public static void writeBoolean(final Parcel parcel, final boolean b) {
        parcel.writeString(String.valueOf(b));
    }
    
    public static void writeInt(final Parcel parcel, final int n) {
        parcel.writeString(String.valueOf(n));
    }
    
    public static void writeLong(final Parcel parcel, final long n) {
        parcel.writeString(String.valueOf(n));
    }
    
    public static void writeString(final Parcel parcel, final String s) {
        parcel.writeString(validateString(s));
    }
}
