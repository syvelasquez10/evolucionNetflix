// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.internal.u;
import android.content.Context;
import android.app.Activity;
import com.google.android.gms.dynamic.f;
import android.app.Fragment;
import com.google.android.gms.dynamic.a;

class StreetViewPanoramaFragment$b extends a<StreetViewPanoramaFragment$a>
{
    private final Fragment Sb;
    protected f<StreetViewPanoramaFragment$a> aiI;
    private Activity nr;
    
    StreetViewPanoramaFragment$b(final Fragment sb) {
        this.Sb = sb;
    }
    
    private void setActivity(final Activity nr) {
        this.nr = nr;
        this.my();
    }
    
    @Override
    protected void a(final f<StreetViewPanoramaFragment$a> aiI) {
        this.aiI = aiI;
        this.my();
    }
    
    public void my() {
        if (this.nr == null || this.aiI == null || this.it() != null) {
            return;
        }
        try {
            MapsInitializer.initialize((Context)this.nr);
            this.aiI.a(new StreetViewPanoramaFragment$a(this.Sb, u.R((Context)this.nr).k(e.k(this.nr))));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
