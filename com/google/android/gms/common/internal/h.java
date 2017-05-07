// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.internal.ne;
import android.content.Context;

public final class h
{
    private final String LX;
    
    public h(final String s) {
        this.LX = n.i(s);
    }
    
    public void a(final Context context, final String s, final String s2, final Throwable t) {
        final StackTraceElement[] stackTrace = t.getStackTrace();
        final StringBuilder sb = new StringBuilder();
        for (int n = 0; n < stackTrace.length && n < 2; ++n) {
            sb.append(stackTrace[n].toString());
            sb.append("\n");
        }
        final ne ne = new ne(context, 10);
        ne.a("GMS_WTF", null, "GMS_WTF", sb.toString());
        ne.send();
        if (this.aC(7)) {
            Log.e(s, s2, t);
            Log.wtf(s, s2, t);
        }
    }
    
    public void a(final String s, final String s2, final Throwable t) {
        if (this.aC(4)) {
            Log.i(s, s2, t);
        }
    }
    
    public boolean aC(final int n) {
        return Log.isLoggable(this.LX, n);
    }
    
    public void b(final String s, final String s2, final Throwable t) {
        if (this.aC(5)) {
            Log.w(s, s2, t);
        }
    }
    
    public void c(final String s, final String s2, final Throwable t) {
        if (this.aC(6)) {
            Log.e(s, s2, t);
        }
    }
    
    public void n(final String s, final String s2) {
        if (this.aC(3)) {
            Log.d(s, s2);
        }
    }
    
    public void o(final String s, final String s2) {
        if (this.aC(2)) {
            Log.v(s, s2);
        }
    }
    
    public void p(final String s, final String s2) {
        if (this.aC(5)) {
            Log.w(s, s2);
        }
    }
    
    public void q(final String s, final String s2) {
        if (this.aC(6)) {
            Log.e(s, s2);
        }
    }
}
