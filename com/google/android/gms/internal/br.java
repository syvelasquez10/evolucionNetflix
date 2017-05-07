// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.dynamic.e;

public final class br extends e<bt>
{
    private static final br ha;
    
    static {
        ha = new br();
    }
    
    private br() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    public static bs a(final Activity activity) {
        try {
            if (b(activity)) {
                ct.r("Using AdOverlay from the client jar.");
                return new bk(activity);
            }
            return br.ha.c(activity);
        }
        catch (a a) {
            ct.v(a.getMessage());
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
    
    private bs c(final Activity activity) {
        try {
            return bs.a.m(this.t((Context)activity).a(c.h(activity)));
        }
        catch (RemoteException ex) {
            ct.b("Could not create remote AdOverlay.", (Throwable)ex);
            return null;
        }
        catch (e.a a) {
            ct.b("Could not create remote AdOverlay.", a);
            return null;
        }
    }
    
    protected bt l(final IBinder binder) {
        return bt.a.n(binder);
    }
    
    private static final class a extends Exception
    {
        public a(final String s) {
            super(s);
        }
    }
}
