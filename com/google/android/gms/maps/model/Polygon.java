// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.g;

public final class Polygon
{
    private final g ajZ;
    
    public Polygon(final g g) {
        this.ajZ = n.i(g);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Polygon)) {
            return false;
        }
        try {
            return this.ajZ.a(((Polygon)o).ajZ);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getFillColor() {
        try {
            return this.ajZ.getFillColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<List<LatLng>> getHoles() {
        try {
            return (List<List<LatLng>>)this.ajZ.getHoles();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.ajZ.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<LatLng> getPoints() {
        try {
            return this.ajZ.getPoints();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getStrokeColor() {
        try {
            return this.ajZ.getStrokeColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getStrokeWidth() {
        try {
            return this.ajZ.getStrokeWidth();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.ajZ.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.ajZ.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isGeodesic() {
        try {
            return this.ajZ.isGeodesic();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.ajZ.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.ajZ.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFillColor(final int fillColor) {
        try {
            this.ajZ.setFillColor(fillColor);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setGeodesic(final boolean geodesic) {
        try {
            this.ajZ.setGeodesic(geodesic);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setHoles(final List<? extends List<LatLng>> holes) {
        try {
            this.ajZ.setHoles(holes);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPoints(final List<LatLng> points) {
        try {
            this.ajZ.setPoints(points);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStrokeColor(final int strokeColor) {
        try {
            this.ajZ.setStrokeColor(strokeColor);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStrokeWidth(final float strokeWidth) {
        try {
            this.ajZ.setStrokeWidth(strokeWidth);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.ajZ.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.ajZ.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
