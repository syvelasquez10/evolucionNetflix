// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Context;
import java.util.Locale;

public class hr
{
    private static final String TAG;
    private final hf<ha> Ok;
    private final hs Rj;
    private final Locale Rk;
    
    static {
        TAG = hr.class.getSimpleName();
    }
    
    public hr(final Context context, final String s, final hf<ha> ok) {
        this.Ok = ok;
        this.Rk = Locale.getDefault();
        this.Rj = new hs(s, this.Rk);
    }
}
