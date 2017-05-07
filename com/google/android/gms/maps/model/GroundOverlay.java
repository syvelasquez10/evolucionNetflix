// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.c;

public final class GroundOverlay
{
    private final c ajC;
    
    public GroundOverlay(final c c) {
        this.ajC = n.i(c);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof GroundOverlay)) {
            return false;
        }
        try {
            return this.ajC.a(((GroundOverlay)o).ajC);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getBearing() {
        try {
            return this.ajC.getBearing();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLngBounds getBounds() {
        try {
            return this.ajC.getBounds();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getHeight() {
        try {
            return this.ajC.getHeight();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.ajC.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLng getPosition() {
        try {
            return this.ajC.getPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getTransparency() {
        try {
            return this.ajC.getTransparency();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getWidth() {
        try {
            return this.ajC.getWidth();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.ajC.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.ajC.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.ajC.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.ajC.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setBearing(final float bearing) {
        try {
            this.ajC.setBearing(bearing);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDimensions(final float dimensions) {
        try {
            this.ajC.setDimensions(dimensions);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDimensions(final float n, final float n2) {
        try {
            this.ajC.a(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setImage(final BitmapDescriptor bitmapDescriptor) {
        try {
            this.ajC.m(bitmapDescriptor.mm());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.ajC.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPositionFromBounds(final LatLngBounds positionFromBounds) {
        try {
            this.ajC.setPositionFromBounds(positionFromBounds);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTransparency(final float transparency) {
        try {
            this.ajC.setTransparency(transparency);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.ajC.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.ajC.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
