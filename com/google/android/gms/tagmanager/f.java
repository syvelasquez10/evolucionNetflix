// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;
import android.content.Context;

class f extends aj
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = a.w.toString();
    }
    
    public f(final Context mContext) {
        super(f.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        return di.u(this.mContext.getPackageName());
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
