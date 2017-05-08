// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.fbui.textlayoutbuilder.util.LayoutMeasureUtil;
import android.graphics.Canvas;
import android.text.Layout;

final class DrawTextLayout extends AbstractDrawCommand
{
    private Layout mLayout;
    private float mLayoutHeight;
    private float mLayoutWidth;
    
    DrawTextLayout(final Layout layout) {
        this.setLayout(layout);
    }
    
    public float getLayoutHeight() {
        return this.mLayoutHeight;
    }
    
    public float getLayoutWidth() {
        return this.mLayoutWidth;
    }
    
    @Override
    protected void onDraw(final Canvas canvas) {
        final float left = this.getLeft();
        final float top = this.getTop();
        canvas.translate(left, top);
        this.mLayout.draw(canvas);
        canvas.translate(-left, -top);
    }
    
    public void setLayout(final Layout mLayout) {
        this.mLayout = mLayout;
        this.mLayoutWidth = mLayout.getWidth();
        this.mLayoutHeight = LayoutMeasureUtil.getHeight(mLayout);
    }
}
