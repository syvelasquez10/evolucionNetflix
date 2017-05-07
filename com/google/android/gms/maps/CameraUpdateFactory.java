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
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;

public final class CameraUpdateFactory
{
    private static ICameraUpdateFactoryDelegate aib;
    
    static void a(final ICameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        if (CameraUpdateFactory.aib != null) {
            return;
        }
        CameraUpdateFactory.aib = n.i(cameraUpdateFactoryDelegate);
    }
    
    private static ICameraUpdateFactoryDelegate mn() {
        return n.b(CameraUpdateFactory.aib, "CameraUpdateFactory is not initialized");
    }
    
    public static CameraUpdate newCameraPosition(final CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(mn().newCameraPosition(cameraPosition));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLng(final LatLng latLng) {
        try {
            return new CameraUpdate(mn().newLatLng(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n) {
        try {
            return new CameraUpdate(mn().newLatLngBounds(latLngBounds, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) {
        try {
            return new CameraUpdate(mn().newLatLngBoundsWithSize(latLngBounds, n, n2, n3));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngZoom(final LatLng latLng, final float n) {
        try {
            return new CameraUpdate(mn().newLatLngZoom(latLng, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate scrollBy(final float n, final float n2) {
        try {
            return new CameraUpdate(mn().scrollBy(n, n2));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n) {
        try {
            return new CameraUpdate(mn().zoomBy(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n, final Point point) {
        try {
            return new CameraUpdate(mn().zoomByWithFocus(n, point.x, point.y));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(mn().zoomIn());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(mn().zoomOut());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomTo(final float n) {
        try {
            return new CameraUpdate(mn().zoomTo(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
