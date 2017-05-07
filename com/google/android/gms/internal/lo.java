// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

public class lo
{
    private final String Dd;
    private final md<lw> Dh;
    private final String IH;
    private lp aep;
    private final Context mContext;
    
    private lo(final Context mContext, final String dd, final String ih, final md<lw> dh) {
        this.mContext = mContext;
        this.Dd = dd;
        this.Dh = dh;
        this.aep = null;
        this.IH = ih;
    }
    
    public static lo a(final Context context, final String s, final String s2, final md<lw> md) {
        return new lo(context, s, s2, md);
    }
}
