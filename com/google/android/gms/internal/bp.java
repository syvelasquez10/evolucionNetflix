// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;

public final class bp extends bq.a
{
    private Map<Class<? extends NetworkExtras>, NetworkExtras> nB;
    private Map<Class<? extends MediationAdapter>, Bundle> nC;
    
    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> br n(final String s) throws RemoteException {
        try {
            final Class<?> forName = Class.forName(s, false, bp.class.getClassLoader());
            if (com.google.ads.mediation.MediationAdapter.class.isAssignableFrom(forName)) {
                final com.google.ads.mediation.MediationAdapter mediationAdapter = (com.google.ads.mediation.MediationAdapter)forName.newInstance();
                return new bw<Object, Object>(mediationAdapter, (com.google.ads.mediation.NetworkExtras)this.nB.get(mediationAdapter.getAdditionalParametersType()));
            }
            if (MediationAdapter.class.isAssignableFrom(forName)) {
                return new bu((MediationAdapter)forName.newInstance(), this.nC.get(forName));
            }
            dw.z("Could not instantiate mediation adapter: " + s + " (not a valid adapter).");
            throw new RemoteException();
        }
        catch (Throwable t) {
            dw.z("Could not instantiate mediation adapter: " + s + ". " + t.getMessage());
            throw new RemoteException();
        }
    }
    
    public void c(final Map<Class<? extends NetworkExtras>, NetworkExtras> nb) {
        this.nB = nb;
    }
    
    public void d(final Map<Class<? extends MediationAdapter>, Bundle> nc) {
        this.nC = nc;
    }
    
    public br m(final String s) throws RemoteException {
        return this.n(s);
    }
}
