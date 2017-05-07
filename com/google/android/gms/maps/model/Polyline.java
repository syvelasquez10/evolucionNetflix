// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

public final class Polyline
{
    private final IPolylineDelegate akd;
    
    public Polyline(final IPolylineDelegate polylineDelegate) {
        this.akd = n.i(polylineDelegate);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Polyline)) {
            return false;
        }
        try {
            return this.akd.equalsRemote(((Polyline)o).akd);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getColor() {
        try {
            return this.akd.getColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.akd.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<LatLng> getPoints() {
        try {
            return this.akd.getPoints();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getWidth() {
        try {
            return this.akd.getWidth();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.akd.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.akd.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isGeodesic() {
        try {
            return this.akd.isGeodesic();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.akd.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.akd.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setColor(final int color) {
        try {
            this.akd.setColor(color);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setGeodesic(final boolean geodesic) {
        try {
            this.akd.setGeodesic(geodesic);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPoints(final List<LatLng> points) {
        try {
            this.akd.setPoints(points);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.akd.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setWidth(final float width) {
        try {
            this.akd.setWidth(width);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.akd.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
