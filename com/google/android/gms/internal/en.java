// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.dynamic.g;

@ez
public final class en extends g<ej>
{
    private static final en sK;
    
    static {
        sK = new en();
    }
    
    private en() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }
    
    private static boolean c(final Activity activity) throws a {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
            throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
    }
    
    public static ei e(final Activity activity) {
        try {
            if (c(activity)) {
                gs.S("Using AdOverlay from the client jar.");
                return new dz(activity);
            }
            return en.sK.f(activity);
        }
        catch (a a) {
            gs.W(a.getMessage());
            return null;
        }
    }
    
    private ei f(final Activity activity) {
        try {
            return ei.a.u(this.L((Context)activity).b(e.k(activity)));
        }
        catch (RemoteException ex) {
            gs.d("Could not create remote InAppPurchaseManager.", (Throwable)ex);
            return null;
        }
        catch (g.a a) {
            gs.d("Could not create remote InAppPurchaseManager.", a);
            return null;
        }
    }
    
    protected ej y(final IBinder binder) {
        return ej.a.v(binder);
    }
    
    private static final class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
}
