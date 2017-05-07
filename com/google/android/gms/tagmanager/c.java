// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.Map;
import android.content.Context;

class c extends aj
{
    private static final String ID;
    private final a anH;
    
    static {
        ID = com.google.android.gms.internal.a.v.toString();
    }
    
    public c(final Context context) {
        this(a.V(context));
    }
    
    c(final a anH) {
        super(c.ID, new String[0]);
        this.anH = anH;
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        return di.u(!this.anH.isLimitAdTrackingEnabled());
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
