// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;

public final class UiSettings
{
    private final IUiSettingsDelegate Sy;
    
    UiSettings(final IUiSettingsDelegate sy) {
        this.Sy = sy;
    }
    
    public boolean isCompassEnabled() {
        try {
            return this.Sy.isCompassEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isIndoorLevelPickerEnabled() {
        try {
            return this.Sy.isIndoorLevelPickerEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isMyLocationButtonEnabled() {
        try {
            return this.Sy.isMyLocationButtonEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isRotateGesturesEnabled() {
        try {
            return this.Sy.isRotateGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isScrollGesturesEnabled() {
        try {
            return this.Sy.isScrollGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isTiltGesturesEnabled() {
        try {
            return this.Sy.isTiltGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomControlsEnabled() {
        try {
            return this.Sy.isZoomControlsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isZoomGesturesEnabled() {
        try {
            return this.Sy.isZoomGesturesEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setAllGesturesEnabled(final boolean allGesturesEnabled) {
        try {
            this.Sy.setAllGesturesEnabled(allGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setCompassEnabled(final boolean compassEnabled) {
        try {
            this.Sy.setCompassEnabled(compassEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setIndoorLevelPickerEnabled(final boolean indoorLevelPickerEnabled) {
        try {
            this.Sy.setIndoorLevelPickerEnabled(indoorLevelPickerEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setMyLocationButtonEnabled(final boolean myLocationButtonEnabled) {
        try {
            this.Sy.setMyLocationButtonEnabled(myLocationButtonEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setRotateGesturesEnabled(final boolean rotateGesturesEnabled) {
        try {
            this.Sy.setRotateGesturesEnabled(rotateGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setScrollGesturesEnabled(final boolean scrollGesturesEnabled) {
        try {
            this.Sy.setScrollGesturesEnabled(scrollGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setTiltGesturesEnabled(final boolean tiltGesturesEnabled) {
        try {
            this.Sy.setTiltGesturesEnabled(tiltGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomControlsEnabled(final boolean zoomControlsEnabled) {
        try {
            this.Sy.setZoomControlsEnabled(zoomControlsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public void setZoomGesturesEnabled(final boolean zoomGesturesEnabled) {
        try {
            this.Sy.setZoomGesturesEnabled(zoomGesturesEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
