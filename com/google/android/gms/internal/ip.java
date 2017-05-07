// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.text.TextUtils;

public class ip
{
    private static boolean GX;
    private boolean GY;
    private boolean GZ;
    private String Ha;
    private final String mTag;
    
    static {
        ip.GX = false;
    }
    
    public ip(final String s) {
        this(s, fT());
    }
    
    public ip(final String mTag, final boolean gy) {
        this.mTag = mTag;
        this.GY = gy;
        this.GZ = false;
    }
    
    private String e(String format, final Object... array) {
        if (array.length != 0) {
            format = String.format(format, array);
        }
        String string = format;
        if (!TextUtils.isEmpty((CharSequence)this.Ha)) {
            string = this.Ha + format;
        }
        return string;
    }
    
    public static boolean fT() {
        return ip.GX;
    }
    
    public void a(final String s, final Object... array) {
        if (this.fS()) {
            Log.v(this.mTag, this.e(s, array));
        }
    }
    
    public void a(final Throwable t, final String s, final Object... array) {
        if (this.fR() || ip.GX) {
            Log.d(this.mTag, this.e(s, array), t);
        }
    }
    
    public void aK(String format) {
        if (TextUtils.isEmpty((CharSequence)format)) {
            format = null;
        }
        else {
            format = String.format("[%s] ", format);
        }
        this.Ha = format;
    }
    
    public void b(final String s, final Object... array) {
        if (this.fR() || ip.GX) {
            Log.d(this.mTag, this.e(s, array));
        }
    }
    
    public void c(final String s, final Object... array) {
        Log.i(this.mTag, this.e(s, array));
    }
    
    public void d(final String s, final Object... array) {
        Log.w(this.mTag, this.e(s, array));
    }
    
    public boolean fR() {
        return this.GY;
    }
    
    public boolean fS() {
        return this.GZ;
    }
}
