// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.internal.fq;
import com.google.android.gms.maps.model.internal.g;

public final class Polygon
{
    private final g Tm;
    
    public Polygon(final g g) {
        this.Tm = fq.f(g);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Polygon)) {
            return false;
        }
        try {
            return this.Tm.a(((Polygon)o).Tm);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getFillColor() {
        try {
            return this.Tm.getFillColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<List<LatLng>> getHoles() {
        try {
            return (List<List<LatLng>>)this.Tm.getHoles();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.Tm.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<LatLng> getPoints() {
        try {
            return this.Tm.getPoints();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getStrokeColor() {
        try {
            return this.Tm.getStrokeColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getStrokeWidth() {
        try {
            return this.Tm.getStrokeWidth();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.Tm.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.Tm.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isGeodesic() {
        try {
            return this.Tm.isGeodesic();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.Tm.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.Tm.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFillColor(final int fillColor) {
        try {
            this.Tm.setFillColor(fillColor);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setGeodesic(final boolean geodesic) {
        try {
            this.Tm.setGeodesic(geodesic);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setHoles(final List<? extends List<LatLng>> holes) {
        try {
            this.Tm.setHoles(holes);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPoints(final List<LatLng> points) {
        try {
            this.Tm.setPoints(points);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStrokeColor(final int strokeColor) {
        try {
            this.Tm.setStrokeColor(strokeColor);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStrokeWidth(final float strokeWidth) {
        try {
            this.Tm.setStrokeWidth(strokeWidth);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.Tm.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.Tm.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
