// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;

public class ie
{
    private final md<lw> Dh;
    private final Context mContext;
    
    private ie(final Context mContext, final md<lw> dh) {
        this.mContext = mContext;
        this.Dh = dh;
    }
    
    public static ie a(final Context context, final md<lw> md) {
        return new ie(context, md);
    }
}
