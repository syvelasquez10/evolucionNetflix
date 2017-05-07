// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.h;

public final class TileOverlay
{
    private final h akf;
    
    public TileOverlay(final h h) {
        this.akf = n.i(h);
    }
    
    public void clearTileCache() {
        try {
            this.akf.clearTileCache();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof TileOverlay)) {
            return false;
        }
        try {
            return this.akf.a(((TileOverlay)o).akf);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean getFadeIn() {
        try {
            return this.akf.getFadeIn();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getId() {
        try {
            return this.akf.getId();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public float getZIndex() {
        try {
            return this.akf.getZIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.akf.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isVisible() {
        try {
            return this.akf.isVisible();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void remove() {
        try {
            this.akf.remove();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setFadeIn(final boolean fadeIn) {
        try {
            this.akf.setFadeIn(fadeIn);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setVisible(final boolean visible) {
        try {
            this.akf.setVisible(visible);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZIndex(final float zIndex) {
        try {
            this.akf.setZIndex(zIndex);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
