// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.systrace;

import android.os.Trace;
import android.os.Build$VERSION;

public class Systrace
{
    public static void beginAsyncSection(final long n, final String s, final int n2) {
    }
    
    public static void beginSection(final long n, final String s) {
        if (Build$VERSION.SDK_INT >= 18) {
            Trace.beginSection(s);
        }
    }
    
    public static void endAsyncFlow(final long n, final String s, final int n2) {
    }
    
    public static void endAsyncSection(final long n, final String s, final int n2) {
    }
    
    public static void endSection(final long n) {
        if (Build$VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }
    
    public static boolean isTracing(final long n) {
        return false;
    }
    
    public static void registerListener(final TraceListener traceListener) {
    }
    
    public static void startAsyncFlow(final long n, final String s, final int n2) {
    }
    
    public static void traceCounter(final long n, final String s, final int n2) {
    }
    
    public static void traceInstant(final long n, final String s, final Systrace$EventScope systrace$EventScope) {
    }
    
    public static void unregisterListener(final TraceListener traceListener) {
    }
}
