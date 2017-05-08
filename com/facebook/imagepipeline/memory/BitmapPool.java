// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import android.graphics.Bitmap$Config;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import android.annotation.TargetApi;
import android.graphics.Bitmap;

@TargetApi(21)
public class BitmapPool extends BasePool<Bitmap>
{
    public BitmapPool(final MemoryTrimmableRegistry memoryTrimmableRegistry, final PoolParams poolParams, final PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        this.initialize();
    }
    
    @Override
    protected Bitmap alloc(final int n) {
        return Bitmap.createBitmap(1, (int)Math.ceil(n / 2.0), Bitmap$Config.RGB_565);
    }
    
    @Override
    protected void free(final Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        bitmap.recycle();
    }
    
    @Override
    protected int getBucketedSize(final int n) {
        return n;
    }
    
    @Override
    protected int getBucketedSizeForValue(final Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        return bitmap.getAllocationByteCount();
    }
    
    @Override
    protected int getSizeInBytes(final int n) {
        return n;
    }
    
    @Override
    protected boolean isReusable(final Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        return !bitmap.isRecycled() && bitmap.isMutable();
    }
}
