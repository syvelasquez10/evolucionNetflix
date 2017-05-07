// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class dv implements SafeParcelable
{
    private static final Object pa;
    private static ClassLoader pb;
    private static Integer pc;
    private boolean pd;
    
    static {
        pa = new Object();
        dv.pb = null;
        dv.pc = null;
    }
    
    public dv() {
        this.pd = false;
    }
    
    protected static boolean P(final String s) {
        final ClassLoader bl = bL();
        if (bl == null) {
            return true;
        }
        try {
            return a(bl.loadClass(s));
        }
        catch (Exception ex) {
            return false;
        }
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
    
    protected static ClassLoader bL() {
        synchronized (dv.pa) {
            return dv.pb;
        }
    }
    
    protected static Integer bM() {
        synchronized (dv.pa) {
            return dv.pc;
        }
    }
    
    protected boolean bN() {
        return this.pd;
    }
}
