// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Canvas;

public abstract class DrawCommand
{
    static final DrawCommand[] EMPTY_ARRAY;
    
    static {
        EMPTY_ARRAY = new DrawCommand[0];
    }
    
    abstract void debugDraw(final FlatViewGroup p0, final Canvas p1);
    
    abstract void draw(final FlatViewGroup p0, final Canvas p1);
}
