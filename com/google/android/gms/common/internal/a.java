// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import android.os.Looper;

public final class a
{
    public static void I(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    public static void a(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    public static void aT(final String s) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            Log.e("Asserts", "checkMainThread: current thread " + Thread.currentThread() + " IS NOT the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(s);
        }
    }
    
    public static void aU(final String s) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Log.e("Asserts", "checkNotMainThread: current thread " + Thread.currentThread() + " IS the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(s);
        }
    }
    
    public static void f(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null reference");
        }
    }
}
