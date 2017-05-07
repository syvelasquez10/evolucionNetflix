// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import android.os.IInterface;

public interface IPolylineDelegate extends IInterface
{
    boolean equalsRemote(final IPolylineDelegate p0);
    
    int getColor();
    
    String getId();
    
    List<LatLng> getPoints();
    
    float getWidth();
    
    float getZIndex();
    
    int hashCodeRemote();
    
    boolean isGeodesic();
    
    boolean isVisible();
    
    void remove();
    
    void setColor(final int p0);
    
    void setGeodesic(final boolean p0);
    
    void setPoints(final List<LatLng> p0);
    
    void setVisible(final boolean p0);
    
    void setWidth(final float p0);
    
    void setZIndex(final float p0);
}
