// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.IInterface;

public interface IUiSettingsDelegate extends IInterface
{
    boolean isCompassEnabled();
    
    boolean isIndoorLevelPickerEnabled();
    
    boolean isMyLocationButtonEnabled();
    
    boolean isRotateGesturesEnabled();
    
    boolean isScrollGesturesEnabled();
    
    boolean isTiltGesturesEnabled();
    
    boolean isZoomControlsEnabled();
    
    boolean isZoomGesturesEnabled();
    
    void setAllGesturesEnabled(final boolean p0);
    
    void setCompassEnabled(final boolean p0);
    
    void setIndoorLevelPickerEnabled(final boolean p0);
    
    void setMyLocationButtonEnabled(final boolean p0);
    
    void setRotateGesturesEnabled(final boolean p0);
    
    void setScrollGesturesEnabled(final boolean p0);
    
    void setTiltGesturesEnabled(final boolean p0);
    
    void setZoomControlsEnabled(final boolean p0);
    
    void setZoomGesturesEnabled(final boolean p0);
}
