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
import com.google.android.gms.internal.fq;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;

public final class CameraUpdateFactory
{
    private static ICameraUpdateFactoryDelegate Ro;
    
    static void a(final ICameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        if (CameraUpdateFactory.Ro != null) {
            return;
        }
        CameraUpdateFactory.Ro = fq.f(cameraUpdateFactoryDelegate);
    }
    
    private static ICameraUpdateFactoryDelegate ie() {
        return fq.b(CameraUpdateFactory.Ro, "CameraUpdateFactory is not initialized");
    }
    
    public static CameraUpdate newCameraPosition(final CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(ie().newCameraPosition(cameraPosition));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLng(final LatLng latLng) {
        try {
            return new CameraUpdate(ie().newLatLng(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n) {
        try {
            return new CameraUpdate(ie().newLatLngBounds(latLngBounds, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) {
        try {
            return new CameraUpdate(ie().newLatLngBoundsWithSize(latLngBounds, n, n2, n3));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngZoom(final LatLng latLng, final float n) {
        try {
            return new CameraUpdate(ie().newLatLngZoom(latLng, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate scrollBy(final float n, final float n2) {
        try {
            return new CameraUpdate(ie().scrollBy(n, n2));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n) {
        try {
            return new CameraUpdate(ie().zoomBy(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n, final Point point) {
        try {
            return new CameraUpdate(ie().zoomByWithFocus(n, point.x, point.y));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(ie().zoomIn());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(ie().zoomOut());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomTo(final float n) {
        try {
            return new CameraUpdate(ie().zoomTo(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
