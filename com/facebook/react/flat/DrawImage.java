// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.bridge.ReadableArray;
import android.content.Context;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;

interface DrawImage extends AttachDetachListener
{
    int getBorderColor();
    
    float getBorderRadius();
    
    float getBorderWidth();
    
    ScalingUtils$ScaleType getScaleType();
    
    void setBorderColor(final int p0);
    
    void setBorderRadius(final float p0);
    
    void setBorderWidth(final float p0);
    
    void setFadeDuration(final int p0);
    
    void setProgressiveRenderingEnabled(final boolean p0);
    
    void setReactTag(final int p0);
    
    void setScaleType(final ScalingUtils$ScaleType p0);
    
    void setSource(final Context p0, final ReadableArray p1);
    
    void setTintColor(final int p0);
}
