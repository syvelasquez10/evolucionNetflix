// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import android.provider.Settings$Secure;
import com.google.android.gms.internal.a;
import android.content.Context;

class z extends aj
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = a.X.toString();
    }
    
    public z(final Context mContext) {
        super(z.ID, new String[0]);
        this.mContext = mContext;
    }
    
    protected String G(final Context context) {
        return Settings$Secure.getString(context.getContentResolver(), "android_id");
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final String g = this.G(this.mContext);
        if (g == null) {
            return dh.lT();
        }
        return dh.r(g);
    }
}
