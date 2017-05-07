// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;

public final class UiSettings
{
    private final IUiSettingsDelegate Ch;
    
    UiSettings(final IUiSettingsDelegate ch) {
        this.Ch = ch;
    }
    
    public boolean isCompassEnabled() {
        try {
            return this.Ch.isCompassEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isMyLocationButtonEnabled() {
        try {
            return this.Ch.isMyLocationButtonEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isRotateGesturesEnabled() {
        try {
            return this.Ch.isRotateGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isScrollGesturesEnabled() {
        try {
            return this.Ch.isScrollGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isTiltGesturesEnabled() {
        try {
            return this.Ch.isTiltGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomControlsEnabled() {
        try {
            return this.Ch.isZoomControlsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomGesturesEnabled() {
        try {
            return this.Ch.isZoomGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAllGesturesEnabled(final boolean allGesturesEnabled) {
        try {
            this.Ch.setAllGesturesEnabled(allGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setCompassEnabled(final boolean compassEnabled) {
        try {
            this.Ch.setCompassEnabled(compassEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setMyLocationButtonEnabled(final boolean myLocationButtonEnabled) {
        try {
            this.Ch.setMyLocationButtonEnabled(myLocationButtonEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotateGesturesEnabled(final boolean rotateGesturesEnabled) {
        try {
            this.Ch.setRotateGesturesEnabled(rotateGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setScrollGesturesEnabled(final boolean scrollGesturesEnabled) {
        try {
            this.Ch.setScrollGesturesEnabled(scrollGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTiltGesturesEnabled(final boolean tiltGesturesEnabled) {
        try {
            this.Ch.setTiltGesturesEnabled(tiltGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomControlsEnabled(final boolean zoomControlsEnabled) {
        try {
            this.Ch.setZoomControlsEnabled(zoomControlsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomGesturesEnabled(final boolean zoomGesturesEnabled) {
        try {
            this.Ch.setZoomGesturesEnabled(zoomGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
