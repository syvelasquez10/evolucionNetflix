// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.fq;
import com.google.android.gms.maps.model.internal.f;

public final class Marker
{
    private final f Te;
    
    public Marker(final f f) {
        this.Te = fq.f(f);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Marker)) {
            return false;
        }
        try {
            return this.Te.h(((Marker)o).Te);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getAlpha() {
        try {
            return this.Te.getAlpha();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.Te.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public LatLng getPosition() {
        try {
            return this.Te.getPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getRotation() {
        try {
            return this.Te.getRotation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getSnippet() {
        try {
            return this.Te.getSnippet();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getTitle() {
        try {
            return this.Te.getTitle();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.Te.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void hideInfoWindow() {
        try {
            this.Te.hideInfoWindow();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isDraggable() {
        try {
            return this.Te.isDraggable();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isFlat() {
        try {
            return this.Te.isFlat();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isInfoWindowShown() {
        try {
            return this.Te.isInfoWindowShown();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.Te.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.Te.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAlpha(final float alpha) {
        try {
            this.Te.setAlpha(alpha);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAnchor(final float n, final float n2) {
        try {
            this.Te.setAnchor(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setDraggable(final boolean draggable) {
        try {
            this.Te.setDraggable(draggable);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFlat(final boolean flat) {
        try {
            this.Te.setFlat(flat);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setIcon(final BitmapDescriptor bitmapDescriptor) {
        try {
            this.Te.l(bitmapDescriptor.id());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setInfoWindowAnchor(final float n, final float n2) {
        try {
            this.Te.setInfoWindowAnchor(n, n2);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setPosition(final LatLng position) {
        try {
            this.Te.setPosition(position);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotation(final float rotation) {
        try {
            this.Te.setRotation(rotation);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setSnippet(final String snippet) {
        try {
            this.Te.setSnippet(snippet);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTitle(final String title) {
        try {
            this.Te.setTitle(title);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.Te.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void showInfoWindow() {
        try {
            this.Te.showInfoWindow();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
