// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class gn implements gl
{
    private static gn Er;
    
    public static gl ft() {
        synchronized (gn.class) {
            if (gn.Er == null) {
                gn.Er = new gn();
            }
            return gn.Er;
        }
    }
    
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
