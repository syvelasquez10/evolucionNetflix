// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.text.TextUtils;

public class er
{
    private static boolean zC;
    private final String mTag;
    private boolean zD;
    private boolean zE;
    private String zF;
    
    static {
        er.zC = false;
    }
    
    public er(final String s) {
        this(s, dR());
    }
    
    public er(final String mTag, final boolean zd) {
        this.mTag = mTag;
        this.zD = zd;
        this.zE = false;
    }
    
    public static boolean dR() {
        return er.zC;
    }
    
    private String e(String s, final Object... array) {
        final String s2 = s = String.format(s, array);
        if (!TextUtils.isEmpty((CharSequence)this.zF)) {
            s = this.zF + s2;
        }
        return s;
    }
    
    public void a(final String s, final Object... array) {
        if (this.dQ()) {
            Log.v(this.mTag, this.e(s, array));
        }
    }
    
    public void a(final Throwable t, final String s, final Object... array) {
        if (this.dP() || er.zC) {
            Log.d(this.mTag, this.e(s, array), t);
        }
    }
    
    public void ab(String format) {
        if (TextUtils.isEmpty((CharSequence)format)) {
            format = null;
        }
        else {
            format = String.format("[%s] ", format);
        }
        this.zF = format;
    }
    
    public void b(final String s, final Object... array) {
        if (this.dP() || er.zC) {
            Log.d(this.mTag, this.e(s, array));
        }
    }
    
    public void c(final String s, final Object... array) {
        Log.i(this.mTag, this.e(s, array));
    }
    
    public void d(final String s, final Object... array) {
        Log.w(this.mTag, this.e(s, array));
    }
    
    public boolean dP() {
        return this.zD;
    }
    
    public boolean dQ() {
        return this.zE;
    }
}
