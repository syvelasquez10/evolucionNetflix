// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;
import com.google.android.gms.dynamic.e;

public final class u extends e<ad>
{
    private static final u ew;
    
    static {
        ew = new u();
    }
    
    private u() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }
    
    public static ac a(final Context context, final x x, final String s, final ba ba) {
        ac b;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0 || (b = u.ew.b(context, x, s, ba)) == null) {
            ct.r("Using AdManager from the client jar.");
            b = new r(context, x, s, ba, new cu(4242000, 4242000, true));
        }
        return b;
    }
    
    private ac b(final Context context, final x x, final String s, final ba ba) {
        try {
            return ac.a.f(this.t(context).a(c.h(context), x, s, ba, 4242000));
        }
        catch (RemoteException ex) {
            ct.b("Could not create remote AdManager.", (Throwable)ex);
            return null;
        }
        catch (a a) {
            ct.b("Could not create remote AdManager.", a);
            return null;
        }
    }
    
    protected ad c(final IBinder binder) {
        return ad.a.g(binder);
    }
}
