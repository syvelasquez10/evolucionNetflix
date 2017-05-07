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

class MapView$b extends a<MapView$a>
{
    protected f<MapView$a> aiI;
    private final ViewGroup aiN;
    private final GoogleMapOptions aiO;
    private final Context mContext;
    
    MapView$b(final ViewGroup aiN, final Context mContext, final GoogleMapOptions aiO) {
        this.aiN = aiN;
        this.mContext = mContext;
        this.aiO = aiO;
    }
    
    @Override
    protected void a(final f<MapView$a> aiI) {
        this.aiI = aiI;
        this.my();
    }
    
    public void my() {
        if (this.aiI == null || this.it() != null) {
            return;
        }
        try {
            this.aiI.a(new MapView$a(this.aiN, u.R(this.mContext).a(e.k(this.mContext), this.aiO)));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        catch (GooglePlayServicesNotAvailableException ex2) {}
    }
}
