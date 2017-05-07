// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;

public class dk
{
    private static boolean mb;
    private String mTag;
    private boolean mc;
    private boolean md;
    
    static {
        dk.mb = false;
    }
    
    public dk(final String s) {
        this(s, bc());
    }
    
    public dk(final String mTag, final boolean mc) {
        this.mTag = mTag;
        this.mc = mc;
    }
    
    public static boolean bc() {
        return dk.mb;
    }
    
    public void a(final String s, final Object... array) {
        if (this.md) {
            Log.v(this.mTag, String.format(s, array));
        }
    }
    
    public void b(final String s, final Object... array) {
        if (this.mc || dk.mb) {
            Log.d(this.mTag, String.format(s, array));
        }
    }
    
    public void c(final String s, final Object... array) {
        Log.i(this.mTag, String.format(s, array));
    }
    
    public void d(final String s, final Object... array) {
        Log.w(this.mTag, String.format(s, array));
    }
}
