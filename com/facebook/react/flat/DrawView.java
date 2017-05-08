// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;

final class DrawView extends AbstractDrawCommand
{
    public static final DrawView[] EMPTY_ARRAY;
    private final RectF TMP_RECT;
    private float mClipRadius;
    private Path mPath;
    final int reactTag;
    
    static {
        EMPTY_ARRAY = new DrawView[0];
    }
    
    public DrawView(final int reactTag) {
        this.TMP_RECT = new RectF();
        this.reactTag = reactTag;
    }
    
    @Override
    protected void applyClipping(final Canvas canvas) {
        if (this.mClipRadius > 0.5f) {
            canvas.clipPath(this.mPath);
            return;
        }
        super.applyClipping(canvas);
    }
    
    @Override
    public void draw(final FlatViewGroup flatViewGroup, final Canvas canvas) {
        this.onPreDraw(flatViewGroup, canvas);
        if (this.mNeedsClipping || this.mClipRadius > 0.5f) {
            canvas.save(2);
            this.applyClipping(canvas);
            flatViewGroup.drawNextChild(canvas);
            canvas.restore();
            return;
        }
        flatViewGroup.drawNextChild(canvas);
    }
    
    @Override
    protected void onDebugDraw(final FlatViewGroup flatViewGroup, final Canvas canvas) {
        flatViewGroup.debugDrawNextChild(canvas);
    }
    
    @Override
    protected void onDraw(final Canvas canvas) {
    }
}
