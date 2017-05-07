// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;

@ez
public final class cs extends ct.a
{
    private Map<Class<? extends NetworkExtras>, NetworkExtras> qC;
    
    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> cu z(final String s) throws RemoteException {
        try {
            final Class<?> forName = Class.forName(s, false, cs.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(forName)) {
                final MediationAdapter mediationAdapter = (MediationAdapter)forName.newInstance();
                return new cz<Object, Object>(mediationAdapter, (com.google.ads.mediation.NetworkExtras)this.qC.get(mediationAdapter.getAdditionalParametersType()));
            }
            if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(forName)) {
                return new cx((com.google.android.gms.ads.mediation.MediationAdapter)forName.newInstance());
            }
            gs.W("Could not instantiate mediation adapter: " + s + " (not a valid adapter).");
            throw new RemoteException();
        }
        catch (Throwable t) {
            gs.W("Could not instantiate mediation adapter: " + s + ". " + t.getMessage());
            throw new RemoteException();
        }
    }
    
    public void d(final Map<Class<? extends NetworkExtras>, NetworkExtras> qc) {
        this.qC = qc;
    }
    
    public cu x(final String s) throws RemoteException {
        return this.z(s);
    }
    
    public boolean y(final String s) throws RemoteException {
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(s, false, cs.class.getClassLoader()));
        }
        catch (Throwable t) {
            gs.W("Could not load custom event implementation class: " + s + ", assuming old implementation.");
            return false;
        }
    }
}
