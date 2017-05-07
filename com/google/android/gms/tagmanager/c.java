// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import android.content.Context;

class c extends aj
{
    private static final String ID;
    private final a Wz;
    
    static {
        ID = com.google.android.gms.internal.a.v.toString();
    }
    
    public c(final Context context) {
        this(a.E(context));
    }
    
    c(final a wz) {
        super(c.ID, new String[0]);
        this.Wz = wz;
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        return dh.r(!this.Wz.isLimitAdTrackingEnabled());
    }
}
