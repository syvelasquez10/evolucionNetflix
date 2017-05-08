// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import com.crittercism.error.CRXMLHttpRequestException;

public final class cm extends Enum
{
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    
    static {
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        f = new int[] { cm.a, cm.b, cm.c, cm.d, cm.e };
    }
    
    public static int a(final Throwable t) {
        int n = cm.d - 1;
        if (t instanceof CRXMLHttpRequestException) {
            n = cm.e - 1;
        }
        return n;
    }
}
