// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;

public final class UiSettings
{
    private final IUiSettingsDelegate ajl;
    
    UiSettings(final IUiSettingsDelegate ajl) {
        this.ajl = ajl;
    }
    
    public boolean isCompassEnabled() {
        try {
            return this.ajl.isCompassEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isIndoorLevelPickerEnabled() {
        try {
            return this.ajl.isIndoorLevelPickerEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isMyLocationButtonEnabled() {
        try {
            return this.ajl.isMyLocationButtonEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isRotateGesturesEnabled() {
        try {
            return this.ajl.isRotateGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isScrollGesturesEnabled() {
        try {
            return this.ajl.isScrollGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isTiltGesturesEnabled() {
        try {
            return this.ajl.isTiltGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomControlsEnabled() {
        try {
            return this.ajl.isZoomControlsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomGesturesEnabled() {
        try {
            return this.ajl.isZoomGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAllGesturesEnabled(final boolean allGesturesEnabled) {
        try {
            this.ajl.setAllGesturesEnabled(allGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setCompassEnabled(final boolean compassEnabled) {
        try {
            this.ajl.setCompassEnabled(compassEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setIndoorLevelPickerEnabled(final boolean indoorLevelPickerEnabled) {
        try {
            this.ajl.setIndoorLevelPickerEnabled(indoorLevelPickerEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setMyLocationButtonEnabled(final boolean myLocationButtonEnabled) {
        try {
            this.ajl.setMyLocationButtonEnabled(myLocationButtonEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotateGesturesEnabled(final boolean rotateGesturesEnabled) {
        try {
            this.ajl.setRotateGesturesEnabled(rotateGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setScrollGesturesEnabled(final boolean scrollGesturesEnabled) {
        try {
            this.ajl.setScrollGesturesEnabled(scrollGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTiltGesturesEnabled(final boolean tiltGesturesEnabled) {
        try {
            this.ajl.setTiltGesturesEnabled(tiltGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomControlsEnabled(final boolean zoomControlsEnabled) {
        try {
            this.ajl.setZoomControlsEnabled(zoomControlsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomGesturesEnabled(final boolean zoomGesturesEnabled) {
        try {
            this.ajl.setZoomGesturesEnabled(zoomGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
