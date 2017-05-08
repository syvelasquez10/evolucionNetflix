// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Rect;
import android.graphics.Canvas;

abstract class DrawCommandManager
{
    static DrawCommandManager getVerticalClippingInstance(final FlatViewGroup flatViewGroup, final DrawCommand[] array) {
        return new VerticalDrawCommandManager(flatViewGroup, array);
    }
    
    abstract NodeRegion anyNodeRegionWithinBounds(final float p0, final float p1);
    
    abstract void debugDraw(final Canvas p0);
    
    abstract void draw(final Canvas p0);
    
    abstract void getClippingRect(final Rect p0);
    
    abstract boolean updateClippingRect();
    
    abstract NodeRegion virtualNodeRegionWithinBounds(final float p0, final float p1);
}
