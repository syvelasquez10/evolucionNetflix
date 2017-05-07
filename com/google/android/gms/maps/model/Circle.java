// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.b;

public final class Circle
{
    private final b aju;
    
    public Circle(final b b) {
        this.aju = n.i(b);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Circle)) {
            return false;
        }
        try {
            return this.aju.a(((Circle)o).aju);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLng getCenter() {
        try {
            return this.aju.getCenter();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getFillColor() {
        try {
            return this.aju.getFillColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.aju.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public double getRadius() {
        try {
            return this.aju.getRadius();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getStrokeColor() {
        try {
            return this.aju.getStrokeColor();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getStrokeWidth() {
        try {
            return this.aju.getStrokeWidth();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.aju.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.aju.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.aju.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.aju.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setCenter(final LatLng center) {
        try {
            this.aju.setCenter(center);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFillColor(final int fillColor) {
        try {
            this.aju.setFillColor(fillColor);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRadius(final double radius) {
        try {
            this.aju.setRadius(radius);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStrokeColor(final int strokeColor) {
        try {
            this.aju.setStrokeColor(strokeColor);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setStrokeWidth(final float strokeWidth) {
        try {
            this.aju.setStrokeWidth(strokeWidth);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.aju.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.aju.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
