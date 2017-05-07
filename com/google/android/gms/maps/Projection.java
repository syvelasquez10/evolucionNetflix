// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.VisibleRegion;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.maps.model.LatLng;
import android.graphics.Point;
import com.google.android.gms.maps.internal.IProjectionDelegate;

public final class Projection
{
    private final IProjectionDelegate Ce;
    
    Projection(final IProjectionDelegate ce) {
        this.Ce = ce;
    }
    
    public LatLng fromScreenLocation(final Point point) {
        try {
            return this.Ce.fromScreenLocation(c.h(point));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public VisibleRegion getVisibleRegion() {
        try {
            return this.Ce.getVisibleRegion();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public Point toScreenLocation(final LatLng latLng) {
        try {
            return c.b(this.Ce.toScreenLocation(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
