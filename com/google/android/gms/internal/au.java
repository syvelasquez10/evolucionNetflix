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

@ez
public final class au extends g<be>
{
    private static final au nS;
    
    static {
        nS = new au();
    }
    
    private au() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }
    
    public static bd a(final Context context, final ay ay, final String s, final cs cs) {
        bd b;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) != 0 || (b = au.nS.b(context, ay, s, cs)) == null) {
            gs.S("Using AdManager from the client jar.");
            b = new u(context, ay, s, cs, new gt(6111000, 6111000, true));
        }
        return b;
    }
    
    private bd b(final Context context, final ay ay, final String s, final cs cs) {
        try {
            return bd.a.f(this.L(context).a(e.k(context), ay, s, cs, 6111000));
        }
        catch (RemoteException ex) {
            gs.d("Could not create remote AdManager.", (Throwable)ex);
            return null;
        }
        catch (a a) {
            gs.d("Could not create remote AdManager.", a);
            return null;
        }
    }
    
    protected be c(final IBinder binder) {
        return be.a.g(binder);
    }
}
