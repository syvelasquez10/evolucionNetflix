// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

public abstract class iv<T>
{
    private static iv$a JG;
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
                iv.JG = new iv$b(context.getContentResolver());
            }
        }
    }
    
    public static iv<Integer> a(final String s, final Integer n) {
        return new iv$2(s, n);
    }
    
    public static iv<Boolean> g(final String s, final boolean b) {
        return new iv$1(s, Boolean.valueOf(b));
    }
    
    public static iv<String> m(final String s, final String s2) {
        return new iv$3(s, s2);
    }
    
    public String getKey() {
        return this.JH;
    }
}
