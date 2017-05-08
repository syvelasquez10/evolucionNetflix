// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.app.Service;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

public final class at$a extends Enum
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
        f = new int[] { at$a.a, at$a.b, at$a.c, at$a.d, at$a.e };
    }
    
    public static int a(final Context context) {
        if (context instanceof Application) {
            return at$a.a;
        }
        if (context instanceof Activity) {
            return at$a.b;
        }
        if (context instanceof Service) {
            return at$a.c;
        }
        return 0;
    }
}
