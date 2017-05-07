// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.graphics.Point;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.internal.eg;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;

public final class CameraUpdateFactory
{
    private static ICameraUpdateFactoryDelegate Bq;
    
    static void a(final ICameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        if (CameraUpdateFactory.Bq != null) {
            return;
        }
        CameraUpdateFactory.Bq = eg.f(cameraUpdateFactoryDelegate);
    }
    
    private static ICameraUpdateFactoryDelegate em() {
        return eg.b(CameraUpdateFactory.Bq, "CameraUpdateFactory is not initialized");
    }
    
    public static CameraUpdate newCameraPosition(final CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(em().newCameraPosition(cameraPosition));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLng(final LatLng latLng) {
        try {
            return new CameraUpdate(em().newLatLng(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n) {
        try {
            return new CameraUpdate(em().newLatLngBounds(latLngBounds, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) {
        try {
            return new CameraUpdate(em().newLatLngBoundsWithSize(latLngBounds, n, n2, n3));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngZoom(final LatLng latLng, final float n) {
        try {
            return new CameraUpdate(em().newLatLngZoom(latLng, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate scrollBy(final float n, final float n2) {
        try {
            return new CameraUpdate(em().scrollBy(n, n2));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n) {
        try {
            return new CameraUpdate(em().zoomBy(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n, final Point point) {
        try {
            return new CameraUpdate(em().zoomByWithFocus(n, point.x, point.y));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(em().zoomIn());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(em().zoomOut());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomTo(final float n) {
        try {
            return new CameraUpdate(em().zoomTo(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
