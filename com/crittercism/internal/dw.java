// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.util.Log;

public final class dw
{
    public static int a;
    private static ea b;
    private static dw$b c;
    private static dw d;
    
    static {
        dw.a = dw$a.a;
        dw.c = dw$b.d;
        dw.d = new dw();
    }
    
    public static void a(final ea b) {
        dw.b = b;
    }
    
    public static void a(final String s) {
        if (dw.c.a(dw$b.b)) {
            Log.e("Crittercism", s);
        }
    }
    
    public static void a(final String s, final Throwable t) {
        if (dw.c.a(dw$b.b)) {
            Log.e("Crittercism", s, t);
        }
    }
    
    public static void a(final Throwable t) {
        if (dw.c.a(dw$b.e)) {
            Log.d("Crittercism", t.getMessage(), t);
        }
    }
    
    public static void b(final String s) {
        if (dw.c.a(dw$b.c)) {
            Log.w("Crittercism", s);
        }
    }
    
    public static void b(final String s, final Throwable t) {
        if (dw.c.a(dw$b.c)) {
            Log.w("Crittercism", s, t);
        }
    }
    
    public static void b(final Throwable t) {
        if (t instanceof cn) {
            return;
        }
        try {
            if (dw.b != null && dw.a == dw$a.b) {
                final ea b = dw.b;
                final ea$1 ea$1 = new ea$1(b, t, Thread.currentThread().getId());
                if (!b.c.a(ea$1)) {
                    b.b.execute(ea$1);
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {}
    }
    
    public static void c(final String s) {
        if (dw.c.a(dw$b.d)) {
            Log.i("Crittercism", s);
        }
    }
    
    public static void c(final String s, final Throwable t) {
        if (dw.c.a(dw$b.e)) {
            Log.d("Crittercism", s, t);
        }
    }
    
    public static void d(final String s) {
        if (dw.c.a(dw$b.e)) {
            Log.d("Crittercism", s);
        }
    }
}
