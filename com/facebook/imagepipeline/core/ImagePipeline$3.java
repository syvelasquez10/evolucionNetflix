// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.cache.common.CacheKey;
import com.android.internal.util.Predicate;

class ImagePipeline$3 implements Predicate<CacheKey>
{
    final /* synthetic */ ImagePipeline this$0;
    
    ImagePipeline$3(final ImagePipeline this$0) {
        this.this$0 = this$0;
    }
    
    public boolean apply(final CacheKey cacheKey) {
        return true;
    }
}
