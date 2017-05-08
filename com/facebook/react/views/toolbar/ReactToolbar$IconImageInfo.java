// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import com.facebook.imagepipeline.image.ImageInfo;

class ReactToolbar$IconImageInfo implements ImageInfo
{
    private int mHeight;
    private int mWidth;
    
    public ReactToolbar$IconImageInfo(final int mWidth, final int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    @Override
    public int getHeight() {
        return this.mHeight;
    }
    
    @Override
    public int getWidth() {
        return this.mWidth;
    }
}
