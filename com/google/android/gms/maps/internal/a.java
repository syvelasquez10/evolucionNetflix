// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

public final class a
{
    public static Boolean a(final byte b) {
        switch (b) {
            default: {
                return null;
            }
            case 1: {
                return Boolean.TRUE;
            }
            case 0: {
                return Boolean.FALSE;
            }
        }
    }
    
    public static byte c(final Boolean b) {
        if (b == null) {
            return -1;
        }
        if (b) {
            return 1;
        }
        return 0;
    }
}
