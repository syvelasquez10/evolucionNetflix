// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.g$a;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.dynamic.g;

@ez
public final class dr extends g<dt>
{
    private static final dr sh;
    
    static {
        sh = new dr();
    }
    
    private dr() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    public static ds b(final Activity activity) {
        try {
            if (c(activity)) {
                gs.S("Using AdOverlay from the client jar.");
                return new dk(activity);
            }
            return dr.sh.d(activity);
        }
        catch (dr$a dr$a) {
            gs.W(dr$a.getMessage());
            return null;
        }
    }
    
    private static boolean c(final Activity activity) {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            throw new dr$a("Ad overlay requires the useClientJar flag in intent extras.");
        }
        return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
    }
    
    private ds d(final Activity activity) {
        try {
            return ds$a.p(this.L((Context)activity).a(e.k(activity)));
        }
        catch (RemoteException ex) {
            gs.d("Could not create remote AdOverlay.", (Throwable)ex);
            return null;
        }
        catch (g$a g$a) {
            gs.d("Could not create remote AdOverlay.", g$a);
            return null;
        }
    }
    
    protected dt o(final IBinder binder) {
        return dt$a.q(binder);
    }
}
