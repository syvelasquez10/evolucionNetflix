// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.imageutils.BitmapUtil;
import com.facebook.common.internal.Preconditions;
import android.graphics.Bitmap;
import com.facebook.common.references.ResourceReleaser;

public class BitmapCounter
{
    private int mCount;
    private final int mMaxCount;
    private final int mMaxSize;
    private long mSize;
    private final ResourceReleaser<Bitmap> mUnpooledBitmapsReleaser;
    
    public BitmapCounter(final int mMaxCount, final int mMaxSize) {
        final boolean b = true;
        Preconditions.checkArgument(mMaxCount > 0);
        Preconditions.checkArgument(mMaxSize > 0 && b);
        this.mMaxCount = mMaxCount;
        this.mMaxSize = mMaxSize;
        this.mUnpooledBitmapsReleaser = new BitmapCounter$1(this);
    }
    
    public void decrease(final Bitmap bitmap) {
        final boolean b = true;
        synchronized (this) {
            final int sizeInBytes = BitmapUtil.getSizeInBytes(bitmap);
            Preconditions.checkArgument(this.mCount > 0, (Object)"No bitmaps registered.");
            Preconditions.checkArgument(sizeInBytes <= this.mSize && b, "Bitmap size bigger than the total registered size: %d, %d", sizeInBytes, this.mSize);
            this.mSize -= sizeInBytes;
            --this.mCount;
        }
    }
    
    public ResourceReleaser<Bitmap> getReleaser() {
        return this.mUnpooledBitmapsReleaser;
    }
    
    public boolean increase(final Bitmap bitmap) {
        synchronized (this) {
            final int sizeInBytes = BitmapUtil.getSizeInBytes(bitmap);
            boolean b;
            if (this.mCount >= this.mMaxCount || this.mSize + sizeInBytes > this.mMaxSize) {
                b = false;
            }
            else {
                ++this.mCount;
                this.mSize += sizeInBytes;
                b = true;
            }
            return b;
        }
    }
}
