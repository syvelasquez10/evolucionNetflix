// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class fe implements SafeParcelable
{
    private static final Object CW;
    private static ClassLoader CX;
    private static Integer CY;
    private boolean CZ;
    
    static {
        CW = new Object();
        fe.CX = null;
        fe.CY = null;
    }
    
    public fe() {
        this.CZ = false;
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
    
    protected static boolean al(final String s) {
        final ClassLoader ei = eI();
        if (ei == null) {
            return true;
        }
        try {
            return a(ei.loadClass(s));
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected static ClassLoader eI() {
        synchronized (fe.CW) {
            return fe.CX;
        }
    }
    
    protected static Integer eJ() {
        synchronized (fe.CW) {
            return fe.CY;
        }
    }
    
    protected boolean eK() {
        return this.CZ;
    }
}
