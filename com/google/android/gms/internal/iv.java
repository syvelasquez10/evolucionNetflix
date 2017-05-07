// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;

public abstract class iv<T>
{
    private static a JG;
    private static final Object mw;
    protected final String JH;
    protected final T JI;
    private T JJ;
    
    static {
        mw = new Object();
        iv.JG = null;
    }
    
    protected iv(final String jh, final T ji) {
        this.JJ = null;
        this.JH = jh;
        this.JI = ji;
    }
    
    public static void H(final Context context) {
        synchronized (iv.mw) {
            if (iv.JG == null) {
                iv.JG = (a)new b(context.getContentResolver());
            }
        }
    }
    
    public static iv<Integer> a(final String s, final Integer n) {
        return new iv<Integer>(s, n) {};
    }
    
    public static iv<Boolean> g(final String s, final boolean b) {
        return new iv<Boolean>(s, Boolean.valueOf(b)) {};
    }
    
    public static iv<String> m(final String s, final String s2) {
        return new iv<String>(s, s2) {};
    }
    
    public String getKey() {
        return this.JH;
    }
    
    private interface a
    {
    }
    
    private static class b implements a
    {
        private final ContentResolver mContentResolver;
        
        public b(final ContentResolver mContentResolver) {
            this.mContentResolver = mContentResolver;
        }
    }
}
