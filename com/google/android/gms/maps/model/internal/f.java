// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.LatLng;
import android.os.IInterface;

public interface f extends IInterface
{
    float getAlpha();
    
    String getId();
    
    LatLng getPosition();
    
    float getRotation();
    
    String getSnippet();
    
    String getTitle();
    
    boolean h(final f p0);
    
    int hashCodeRemote();
    
    void hideInfoWindow();
    
    boolean isDraggable();
    
    boolean isFlat();
    
    boolean isInfoWindowShown();
    
    boolean isVisible();
    
    void n(final d p0);
    
    void remove();
    
    void setAlpha(final float p0);
    
    void setAnchor(final float p0, final float p1);
    
    void setDraggable(final boolean p0);
    
    void setFlat(final boolean p0);
    
    void setInfoWindowAnchor(final float p0, final float p1);
    
    void setPosition(final LatLng p0);
    
    void setRotation(final float p0);
    
    void setSnippet(final String p0);
    
    void setTitle(final String p0);
    
    void setVisible(final boolean p0);
    
    void showInfoWindow();
}
