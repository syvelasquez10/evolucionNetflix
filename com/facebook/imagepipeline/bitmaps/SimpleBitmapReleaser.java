// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.ResourceReleaser;

public class SimpleBitmapReleaser implements ResourceReleaser<Bitmap>
{
    private static SimpleBitmapReleaser sInstance;
    
    public static SimpleBitmapReleaser getInstance() {
        if (SimpleBitmapReleaser.sInstance == null) {
            SimpleBitmapReleaser.sInstance = new SimpleBitmapReleaser();
        }
        return SimpleBitmapReleaser.sInstance;
    }
    
    @Override
    public void release(final Bitmap bitmap) {
        bitmap.recycle();
    }
}
