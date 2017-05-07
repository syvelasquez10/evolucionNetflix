// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.IInterface;

public interface h extends IInterface
{
    boolean a(final h p0);
    
    void clearTileCache();
    
    boolean getFadeIn();
    
    String getId();
    
    float getZIndex();
    
    int hashCodeRemote();
    
    boolean isVisible();
    
    void remove();
    
    void setFadeIn(final boolean p0);
    
    void setVisible(final boolean p0);
    
    void setZIndex(final float p0);
}
