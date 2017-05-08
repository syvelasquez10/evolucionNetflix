// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

class NodeRegion
{
    static final NodeRegion EMPTY;
    static final NodeRegion[] EMPTY_ARRAY;
    private final float mBottom;
    final boolean mIsVirtual;
    private final float mLeft;
    private final float mRight;
    final int mTag;
    private final float mTop;
    
    static {
        EMPTY_ARRAY = new NodeRegion[0];
        EMPTY = new NodeRegion(0.0f, 0.0f, 0.0f, 0.0f, -1, false);
    }
    
    NodeRegion(final float mLeft, final float mTop, final float mRight, final float mBottom, final int mTag, final boolean mIsVirtual) {
        this.mLeft = mLeft;
        this.mTop = mTop;
        this.mRight = mRight;
        this.mBottom = mBottom;
        this.mTag = mTag;
        this.mIsVirtual = mIsVirtual;
    }
    
    final float getBottom() {
        return this.mBottom;
    }
    
    final float getLeft() {
        return this.mLeft;
    }
    
    int getReactTag(final float n, final float n2) {
        return this.mTag;
    }
    
    final float getRight() {
        return this.mRight;
    }
    
    final float getTop() {
        return this.mTop;
    }
    
    boolean withinBounds(final float n, final float n2) {
        return this.mLeft <= n && n < this.mRight && this.mTop <= n2 && n2 < this.mBottom;
    }
}
