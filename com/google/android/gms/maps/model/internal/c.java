// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import android.os.IInterface;

public interface c extends IInterface
{
    void a(final float p0, final float p1);
    
    boolean a(final c p0);
    
    float getBearing();
    
    LatLngBounds getBounds();
    
    float getHeight();
    
    String getId();
    
    LatLng getPosition();
    
    float getTransparency();
    
    float getWidth();
    
    float getZIndex();
    
    int hashCodeRemote();
    
    boolean isVisible();
    
    void m(final d p0);
    
    void remove();
    
    void setBearing(final float p0);
    
    void setDimensions(final float p0);
    
    void setPosition(final LatLng p0);
    
    void setPositionFromBounds(final LatLngBounds p0);
    
    void setTransparency(final float p0);
    
    void setVisible(final boolean p0);
    
    void setZIndex(final float p0);
}
