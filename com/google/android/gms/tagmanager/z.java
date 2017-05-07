// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.provider.Settings$Secure;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;
import android.content.Context;

class z extends aj
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = a.Z.toString();
    }
    
    public z(final Context mContext) {
        super(z.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final String x = this.X(this.mContext);
        if (x == null) {
            return di.pI();
        }
        return di.u(x);
    }
    
    protected String X(final Context context) {
        return Settings$Secure.getString(context.getContentResolver(), "android_id");
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
