// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.e;
import android.location.Location;
import com.google.android.gms.maps.internal.h;

class GoogleMap$6$1 implements LocationSource$OnLocationChangedListener
{
    final /* synthetic */ h ail;
    final /* synthetic */ GoogleMap$6 aim;
    
    GoogleMap$6$1(final GoogleMap$6 aim, final h ail) {
        this.aim = aim;
        this.ail = ail;
    }
    
    @Override
    public void onLocationChanged(final Location location) {
        try {
            this.ail.l(e.k(location));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
