// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;

public class mv
{
    private static final String TAG;
    private final md<lw> Dh;
    private final mw ahW;
    private final Locale ahX;
    
    static {
        TAG = mv.class.getSimpleName();
    }
    
    public mv(final String s, final md<lw> dh, final String s2) {
        this.Dh = dh;
        this.ahX = Locale.getDefault();
        this.ahW = new mw(s, this.ahX, s2);
    }
}
