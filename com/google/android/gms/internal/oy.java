// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.g$a;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.c;
import android.app.Activity;
import com.google.android.gms.dynamic.g;

public class oy extends g<ot>
{
    private static oy aum;
    
    protected oy() {
        super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
    }
    
    public static oq a(final Activity activity, final c c, final WalletFragmentOptions walletFragmentOptions, final or or) {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity);
        if (googlePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        try {
            return pN().L((Context)activity).a(e.k(activity), c, walletFragmentOptions, or);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        catch (g$a g$a) {
            throw new RuntimeException(g$a);
        }
    }
    
    private static oy pN() {
        if (oy.aum == null) {
            oy.aum = new oy();
        }
        return oy.aum;
    }
    
    protected ot bQ(final IBinder binder) {
        return ot$a.bM(binder);
    }
}
