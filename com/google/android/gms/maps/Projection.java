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
    private final IProjectionDelegate Sc;
    
    Projection(final IProjectionDelegate sc) {
        this.Sc = sc;
    }
    
    public LatLng fromScreenLocation(final Point point) {
        try {
            return this.Sc.fromScreenLocation(e.h(point));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public VisibleRegion getVisibleRegion() {
        try {
            return this.Sc.getVisibleRegion();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public Point toScreenLocation(final LatLng latLng) {
        try {
            return e.d(this.Sc.toScreenLocation(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
