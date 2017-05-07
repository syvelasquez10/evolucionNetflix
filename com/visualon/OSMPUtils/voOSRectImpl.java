// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public class voOSRectImpl implements voOSRect
{
    int mBottom;
    int mLeft;
    int mRight;
    int mTop;
    
    public voOSRectImpl(final int mLeft, final int mTop, final int mRight, final int mBottom) {
        this.mLeft = mLeft;
        this.mTop = mTop;
        this.mRight = mRight;
        this.mBottom = mBottom;
    }
    
    @Override
    public int Bottom() {
        return this.mBottom;
    }
    
    @Override
    public int Left() {
        return this.mLeft;
    }
    
    @Override
    public int Right() {
        return this.mRight;
    }
    
    @Override
    public int Top() {
        return this.mTop;
    }
}
