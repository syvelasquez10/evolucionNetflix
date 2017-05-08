// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import com.facebook.cache.common.WriterCallback;
import java.io.InputStream;
import com.facebook.binaryresource.BinaryResource;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import bolts.Task;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.cache.common.CacheKey;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.cache.disk.FileCache;
import java.util.concurrent.Callable;

class BufferedDiskCache$5 implements Callable<Void>
{
    final /* synthetic */ BufferedDiskCache this$0;
    
    BufferedDiskCache$5(final BufferedDiskCache this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Void call() {
        this.this$0.mStagingArea.clearAll();
        this.this$0.mFileCache.clearAll();
        return null;
    }
}
