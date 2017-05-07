// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import android.os.IInterface;

public interface g extends IInterface
{
    boolean a(final g p0);
    
    int getFillColor();
    
    List getHoles();
    
    String getId();
    
    List<LatLng> getPoints();
    
    int getStrokeColor();
    
    float getStrokeWidth();
    
    float getZIndex();
    
    int hashCodeRemote();
    
    boolean isGeodesic();
    
    boolean isVisible();
    
    void remove();
    
    void setFillColor(final int p0);
    
    void setGeodesic(final boolean p0);
    
    void setHoles(final List p0);
    
    void setPoints(final List<LatLng> p0);
    
    void setStrokeColor(final int p0);
    
    void setStrokeWidth(final float p0);
    
    void setVisible(final boolean p0);
    
    void setZIndex(final float p0);
}
