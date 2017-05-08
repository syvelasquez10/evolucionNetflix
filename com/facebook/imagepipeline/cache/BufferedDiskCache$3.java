// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.cache.common.WriterCallback;
import java.io.InputStream;
import com.facebook.binaryresource.BinaryResource;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import bolts.Task;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.cache.disk.FileCache;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.EncodedImage;

class BufferedDiskCache$3 implements Runnable
{
    final /* synthetic */ BufferedDiskCache this$0;
    final /* synthetic */ EncodedImage val$finalEncodedImage;
    final /* synthetic */ CacheKey val$key;
    
    BufferedDiskCache$3(final BufferedDiskCache this$0, final CacheKey val$key, final EncodedImage val$finalEncodedImage) {
        this.this$0 = this$0;
        this.val$key = val$key;
        this.val$finalEncodedImage = val$finalEncodedImage;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.writeToDiskCache(this.val$key, this.val$finalEncodedImage);
        }
        finally {
            this.this$0.mStagingArea.remove(this.val$key, this.val$finalEncodedImage);
            EncodedImage.closeSafely(this.val$finalEncodedImage);
        }
    }
}
