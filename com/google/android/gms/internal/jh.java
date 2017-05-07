// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.dynamic.c;
import android.app.Activity;
import com.google.android.gms.dynamic.g;

public class jh extends g<jc>
{
    private static jh adc;
    
    protected jh() {
        super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
    }
    
    public static iz a(final Activity activity, final c c, final WalletFragmentOptions walletFragmentOptions, final ja ja) throws GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable((Context)activity);
        if (googlePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        try {
            return lY().z((Context)activity).a(e.h(activity), c, walletFragmentOptions, ja);
        }
        catch (RemoteException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        catch (a a) {
            throw new RuntimeException(a);
        }
    }
    
    private static jh lY() {
        if (jh.adc == null) {
            jh.adc = new jh();
        }
        return jh.adc;
    }
    
    protected jc aZ(final IBinder binder) {
        return jc.a.aV(binder);
    }
}
