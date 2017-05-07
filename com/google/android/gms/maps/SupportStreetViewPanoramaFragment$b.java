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
import android.support.v4.app.Fragment;
import com.google.android.gms.dynamic.a;

class SupportStreetViewPanoramaFragment$b extends a<SupportStreetViewPanoramaFragment$a>
{
    private final Fragment Ll;
    protected f<SupportStreetViewPanoramaFragment$a> aiI;
    private Activity nr;
    
    SupportStreetViewPanoramaFragment$b(final Fragment ll) {
        this.Ll = ll;
    }
    
    private void setActivity(final Activity nr) {
        this.nr = nr;
        this.my();
    }
    
    @Override
    protected void a(final f<SupportStreetViewPanoramaFragment$a> aiI) {
        this.aiI = aiI;
        this.my();
    }
    
    public void my() {
        if (this.nr == null || this.aiI == null || this.it() != null) {
            return;
        }
        try {
            MapsInitializer.initialize((Context)this.nr);
            this.aiI.a(new SupportStreetViewPanoramaFragment$a(this.Ll, u.R((Context)this.nr).k(e.k(this.nr))));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
