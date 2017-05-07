// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.content.Context;
import com.google.android.gms.common.internal.h;

public final class v
{
    private static final h OS;
    
    static {
        OS = new h("GmsDrive");
    }
    
    public static void a(final String s, final Throwable t, final String s2) {
        v.OS.c(s, s2, t);
    }
    
    public static void e(final Context context, final String s, final String s2) {
        v.OS.a(context, s, s2, new Throwable());
    }
    
    public static void n(final String s, final String s2) {
        v.OS.n(s, s2);
    }
    
    public static void p(final String s, final String s2) {
        v.OS.p(s, s2);
    }
    
    public static void q(final String s, final String s2) {
        v.OS.q(s, s2);
    }
}
