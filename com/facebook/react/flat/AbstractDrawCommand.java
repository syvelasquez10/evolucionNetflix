// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Canvas;

abstract class AbstractDrawCommand extends DrawCommand implements Cloneable
{
    private float mBottom;
    private float mClipBottom;
    private float mClipLeft;
    private float mClipRight;
    private float mClipTop;
    private boolean mFrozen;
    private float mLeft;
    protected boolean mNeedsClipping;
    private float mRight;
    private float mTop;
    
    protected static int getDebugBorderColor() {
        return -16711681;
    }
    
    protected void applyClipping(final Canvas canvas) {
        canvas.clipRect(this.mClipLeft, this.mClipTop, this.mClipRight, this.mClipBottom);
    }
    
    public final void debugDraw(final FlatViewGroup flatViewGroup, final Canvas canvas) {
        this.onDebugDraw(flatViewGroup, canvas);
    }
    
    public void draw(final FlatViewGroup flatViewGroup, final Canvas canvas) {
        this.onPreDraw(flatViewGroup, canvas);
        if (this.mNeedsClipping && this.shouldClip()) {
            canvas.save(2);
            this.applyClipping(canvas);
            this.onDraw(canvas);
            canvas.restore();
            return;
        }
        this.onDraw(canvas);
    }
    
    public final float getBottom() {
        return this.mBottom;
    }
    
    public final float getClipBottom() {
        return this.mClipBottom;
    }
    
    public final float getClipLeft() {
        return this.mClipLeft;
    }
    
    public final float getClipRight() {
        return this.mClipRight;
    }
    
    public final float getClipTop() {
        return this.mClipTop;
    }
    
    protected String getDebugName() {
        return this.getClass().getSimpleName().substring(4);
    }
    
    public final float getLeft() {
        return this.mLeft;
    }
    
    public final float getRight() {
        return this.mRight;
    }
    
    public final float getTop() {
        return this.mTop;
    }
    
    public final boolean isFrozen() {
        return this.mFrozen;
    }
    
    public final AbstractDrawCommand mutableCopy() {
        try {
            final AbstractDrawCommand abstractDrawCommand = (AbstractDrawCommand)super.clone();
            abstractDrawCommand.mFrozen = false;
            return abstractDrawCommand;
        }
        catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    protected void onDebugDraw(final FlatViewGroup flatViewGroup, final Canvas canvas) {
        flatViewGroup.debugDrawNamedRect(canvas, getDebugBorderColor(), this.getDebugName(), this.mLeft, this.mTop, this.mRight, this.mBottom);
    }
    
    protected abstract void onDraw(final Canvas p0);
    
    protected void onPreDraw(final FlatViewGroup flatViewGroup, final Canvas canvas) {
    }
    
    protected boolean shouldClip() {
        return this.mLeft < this.getClipLeft() || this.mTop < this.getClipTop() || this.mRight > this.getClipRight() || this.mBottom > this.getClipBottom();
    }
}
