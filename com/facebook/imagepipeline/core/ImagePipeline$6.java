// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.android.internal.util.Predicate;

class ImagePipeline$6 implements Predicate<CacheKey>
{
    final /* synthetic */ ImagePipeline this$0;
    final /* synthetic */ Uri val$uri;
    
    ImagePipeline$6(final ImagePipeline this$0, final Uri val$uri) {
        this.this$0 = this$0;
        this.val$uri = val$uri;
    }
    
    public boolean apply(final CacheKey cacheKey) {
        return cacheKey.containsUri(this.val$uri);
    }
}
