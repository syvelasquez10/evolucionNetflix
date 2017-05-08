// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.disk.DiskTrimmableRegistry;
import android.content.Context;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheErrorLogger;
import java.io.File;
import com.facebook.common.internal.Supplier;

class DiskCacheConfig$Builder$1 implements Supplier<File>
{
    final /* synthetic */ DiskCacheConfig$Builder this$0;
    
    DiskCacheConfig$Builder$1(final DiskCacheConfig$Builder this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public File get() {
        return this.this$0.mContext.getApplicationContext().getCacheDir();
    }
}
