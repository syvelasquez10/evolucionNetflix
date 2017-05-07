// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Base64;

public final class js
{
    public static String d(final byte[] array) {
        if (array == null) {
            return null;
        }
        return Base64.encodeToString(array, 0);
    }
    
    public static String e(final byte[] array) {
        if (array == null) {
            return null;
        }
        return Base64.encodeToString(array, 10);
    }
}
