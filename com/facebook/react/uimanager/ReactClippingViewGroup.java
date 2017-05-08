// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.graphics.Rect;

public interface ReactClippingViewGroup
{
    void getClippingRect(final Rect p0);
    
    boolean getRemoveClippedSubviews();
    
    void updateClippingRect();
}
