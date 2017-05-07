// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;

public final class ba extends bb.a
{
    private Map<Class<? extends NetworkExtras>, NetworkExtras> gf;
    
    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> bc m(final String s) throws RemoteException {
        Class<?> forName;
        try {
            forName = Class.forName(s, false, ba.class.getClassLoader());
            if (!MediationAdapter.class.isAssignableFrom(forName)) {
                ct.v("Could not instantiate mediation adapter: " + s + ".");
                throw new RemoteException();
            }
        }
        catch (Throwable t) {
            ct.v("Could not instantiate mediation adapter: " + s + ". " + t.getMessage());
            throw new RemoteException();
        }
        final MediationAdapter mediationAdapter = (MediationAdapter)forName.newInstance();
        return new be<Object, Object>(mediationAdapter, (com.google.ads.mediation.NetworkExtras)this.gf.get(mediationAdapter.getAdditionalParametersType()));
    }
    
    public void c(final Map<Class<? extends NetworkExtras>, NetworkExtras> gf) {
        this.gf = gf;
    }
    
    public bc l(final String s) throws RemoteException {
        return this.m(s);
    }
}
