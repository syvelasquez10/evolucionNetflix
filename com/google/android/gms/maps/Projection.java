// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.VisibleRegion;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.model.LatLng;
import android.graphics.Point;
import com.google.android.gms.maps.internal.IProjectionDelegate;

public final class Projection
{
    private final IProjectionDelegate aiP;
    
    Projection(final IProjectionDelegate aiP) {
        this.aiP = aiP;
    }
    
    public LatLng fromScreenLocation(final Point point) {
        try {
            return this.aiP.fromScreenLocation(e.k(point));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public VisibleRegion getVisibleRegion() {
        try {
            return this.aiP.getVisibleRegion();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public Point toScreenLocation(final LatLng latLng) {
        try {
            return e.f(this.aiP.toScreenLocation(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
