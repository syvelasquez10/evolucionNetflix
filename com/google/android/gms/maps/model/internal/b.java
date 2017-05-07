// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.LatLng;
import android.os.IInterface;

public interface b extends IInterface
{
    boolean a(final b p0);
    
    LatLng getCenter();
    
    int getFillColor();
    
    String getId();
    
    double getRadius();
    
    int getStrokeColor();
    
    float getStrokeWidth();
    
    float getZIndex();
    
    int hashCodeRemote();
    
    boolean isVisible();
    
    void remove();
    
    void setCenter(final LatLng p0);
    
    void setFillColor(final int p0);
    
    void setRadius(final double p0);
    
    void setStrokeColor(final int p0);
    
    void setStrokeWidth(final float p0);
    
    void setVisible(final boolean p0);
    
    void setZIndex(final float p0);
}
