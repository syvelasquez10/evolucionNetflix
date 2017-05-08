// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.references.ResourceReleaser;

class BitmapCounter$1 implements ResourceReleaser<Bitmap>
{
    final /* synthetic */ BitmapCounter this$0;
    
    BitmapCounter$1(final BitmapCounter this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void release(final Bitmap bitmap) {
        try {
            this.this$0.decrease(bitmap);
        }
        finally {
            bitmap.recycle();
        }
    }
}
