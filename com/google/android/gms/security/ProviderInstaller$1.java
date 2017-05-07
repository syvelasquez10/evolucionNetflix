// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.security;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import android.content.Context;
import android.os.AsyncTask;

final class ProviderInstaller$1 extends AsyncTask<Void, Void, Integer>
{
    final /* synthetic */ ProviderInstaller$ProviderInstallListener anA;
    final /* synthetic */ Context mV;
    
    ProviderInstaller$1(final Context mv, final ProviderInstaller$ProviderInstallListener anA) {
        this.mV = mv;
        this.anA = anA;
    }
    
    protected Integer b(final Void... array) {
        try {
            ProviderInstaller.installIfNeeded(this.mV);
            return 0;
        }
        catch (GooglePlayServicesRepairableException ex) {
            return ex.getConnectionStatusCode();
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            return ex2.errorCode;
        }
    }
    
    protected void d(final Integer n) {
        if (n == 0) {
            this.anA.onProviderInstalled();
            return;
        }
        this.anA.onProviderInstallFailed(n, GooglePlayServicesUtil.ai(n));
    }
}
