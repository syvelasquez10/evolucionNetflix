// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class c implements SafeParcelable
{
    private static final Object Ln;
    private static ClassLoader Lo;
    private static Integer Lp;
    private boolean Lq;
    
    static {
        Ln = new Object();
        c.Lo = null;
        c.Lp = null;
    }
    
    public c() {
        this.Lq = false;
    }
    
    private static boolean a(final Class<?> clazz) {
        try {
            return "SAFE_PARCELABLE_NULL_STRING".equals(clazz.getField("NULL").get(null));
        }
        catch (IllegalAccessException ex) {
            return false;
        }
        catch (NoSuchFieldException ex2) {
            return false;
        }
    }
    
    protected static boolean aV(final String s) {
        final ClassLoader go = gO();
        if (go == null) {
            return true;
        }
        try {
            return a(go.loadClass(s));
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected static ClassLoader gO() {
        synchronized (c.Ln) {
            return c.Lo;
        }
    }
    
    protected static Integer gP() {
        synchronized (c.Ln) {
            return c.Lp;
        }
    }
    
    protected boolean gQ() {
        return this.Lq;
    }
}
