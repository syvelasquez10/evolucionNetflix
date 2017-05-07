// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;
import com.google.android.gms.dynamic.g;

public final class ag extends g<aq>
{
    private static final ag lG;
    
    static {
        lG = new ag();
    }
    
    private ag() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }
    
    public static ap a(final Context context, final ak ak, final String s, final bp bp) {
        ap b;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0 || (b = ag.lG.b(context, ak, s, bp)) == null) {
            dw.v("Using AdManager from the client jar.");
            b = new v(context, ak, s, bp, new dx(4452000, 4452000, true));
        }
        return b;
    }
    
    private ap b(final Context context, final ak ak, final String s, final bp bp) {
        try {
            return ap.a.f(this.z(context).a(e.h(context), ak, s, bp, 4452000));
        }
        catch (RemoteException ex) {
            dw.c("Could not create remote AdManager.", (Throwable)ex);
            return null;
        }
        catch (a a) {
            dw.c("Could not create remote AdManager.", a);
            return null;
        }
    }
    
    protected aq c(final IBinder binder) {
        return aq.a.g(binder);
    }
}
