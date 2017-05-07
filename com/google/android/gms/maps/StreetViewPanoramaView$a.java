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
import android.view.ViewGroup;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.a;

class StreetViewPanoramaView$a extends a<StreetViewPanoramaView$b>
{
    protected f<StreetViewPanoramaView$b> aiI;
    private final ViewGroup aiN;
    private final StreetViewPanoramaOptions ajg;
    private final Context mContext;
    
    StreetViewPanoramaView$a(final ViewGroup aiN, final Context mContext, final StreetViewPanoramaOptions ajg) {
        this.aiN = aiN;
        this.mContext = mContext;
        this.ajg = ajg;
    }
    
    @Override
    protected void a(final f<StreetViewPanoramaView$b> aiI) {
        this.aiI = aiI;
        this.my();
    }
    
    public void my() {
        if (this.aiI == null || this.it() != null) {
            return;
        }
        try {
            this.aiI.a(new StreetViewPanoramaView$b(this.aiN, u.R(this.mContext).a(e.k(this.mContext), this.ajg)));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
