// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;

final class DrawBackgroundColor extends AbstractDrawCommand
{
    private static final Paint PAINT;
    private final int mBackgroundColor;
    
    static {
        PAINT = new Paint();
    }
    
    DrawBackgroundColor(final int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }
    
    public void onDraw(final Canvas canvas) {
        DrawBackgroundColor.PAINT.setColor(this.mBackgroundColor);
        canvas.drawRect(this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), DrawBackgroundColor.PAINT);
    }
}
