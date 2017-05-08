// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.interfaces;

import android.graphics.drawable.Drawable;

public interface SettableDraweeHierarchy extends DraweeHierarchy
{
    void reset();
    
    void setControllerOverlay(final Drawable p0);
    
    void setFailure(final Throwable p0);
    
    void setImage(final Drawable p0, final float p1, final boolean p2);
    
    void setProgress(final float p0, final boolean p1);
    
    void setRetry(final Throwable p0);
}
