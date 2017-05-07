// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.a;
import android.content.Context;

class g extends aj
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = a.x.toString();
    }
    
    public g(final Context mContext) {
        super(g.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        try {
            final PackageManager packageManager = this.mContext.getPackageManager();
            return di.u(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        }
        catch (PackageManager$NameNotFoundException ex) {
            bh.b("App name is not found.", (Throwable)ex);
            return di.pI();
        }
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
