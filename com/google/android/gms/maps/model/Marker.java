// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.f;

public final class Marker
{
    private final f ajR;
    
    public Marker(final f f) {
        this.ajR = n.i(f);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Marker)) {
            return false;
        }
        try {
            return this.ajR.h(((Marker)o).ajR);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getAlpha() {
        try {
            return this.ajR.getAlpha();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.ajR.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLng getPosition() {
        try {
            return this.ajR.getPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getRotation() {
        try {
            return this.ajR.getRotation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getSnippet() {
        try {
            return this.ajR.getSnippet();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getTitle() {
        try {
            return this.ajR.getTitle();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.ajR.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void hideInfoWindow() {
        try {
            this.ajR.hideInfoWindow();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isDraggable() {
        try {
            return this.ajR.isDraggable();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isFlat() {
        try {
            return this.ajR.isFlat();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isInfoWindowShown() {
        try {
            return this.ajR.isInfoWindowShown();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.ajR.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.ajR.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAlpha(final float alpha) {
        try {
            this.ajR.setAlpha(alpha);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAnchor(final float n, final float n2) {
        try {
            this.ajR.setAnchor(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDraggable(final boolean draggable) {
        try {
            this.ajR.setDraggable(draggable);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFlat(final boolean flat) {
        try {
            this.ajR.setFlat(flat);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setIcon(final BitmapDescriptor bitmapDescriptor) {
        try {
            this.ajR.n(bitmapDescriptor.mm());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setInfoWindowAnchor(final float n, final float n2) {
        try {
            this.ajR.setInfoWindowAnchor(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.ajR.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotation(final float rotation) {
        try {
            this.ajR.setRotation(rotation);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setSnippet(final String snippet) {
        try {
            this.ajR.setSnippet(snippet);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTitle(final String title) {
        try {
            this.ajR.setTitle(title);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.ajR.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void showInfoWindow() {
        try {
            this.ajR.showInfoWindow();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
