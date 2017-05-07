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

public final class cj extends g<cl>
{
    private static final cj oC;
    
    static {
        oC = new cj();
    }
    
    private cj() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    public static ck a(final Activity activity) {
        try {
            if (b(activity)) {
                dw.v("Using AdOverlay from the client jar.");
                return new cc(activity);
            }
            return cj.oC.c(activity);
        }
        catch (a a) {
            dw.z(a.getMessage());
            return null;
        }
    }
    
    private static boolean b(final Activity activity) throws a {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            throw new a("Ad overlay requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
    }
    
    private ck c(final Activity activity) {
        try {
            return ck.a.m(this.z((Context)activity).a(e.h(activity)));
        }
        catch (RemoteException ex) {
            dw.c("Could not create remote AdOverlay.", (Throwable)ex);
            return null;
        }
        catch (g.a a) {
            dw.c("Could not create remote AdOverlay.", a);
            return null;
        }
    }
    
    protected cl l(final IBinder binder) {
        return cl.a.n(binder);
    }
    
    private static final class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
}
