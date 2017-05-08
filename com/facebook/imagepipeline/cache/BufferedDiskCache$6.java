// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Preconditions;
import java.io.InputStream;
import com.facebook.binaryresource.BinaryResource;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import bolts.Task;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.cache.common.CacheKey;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.PooledByteStreams;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.cache.disk.FileCache;
import java.io.OutputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.cache.common.WriterCallback;

class BufferedDiskCache$6 implements WriterCallback
{
    final /* synthetic */ BufferedDiskCache this$0;
    final /* synthetic */ EncodedImage val$encodedImage;
    
    BufferedDiskCache$6(final BufferedDiskCache this$0, final EncodedImage val$encodedImage) {
        this.this$0 = this$0;
        this.val$encodedImage = val$encodedImage;
    }
    
    @Override
    public void write(final OutputStream outputStream) {
        this.this$0.mPooledByteStreams.copy(this.val$encodedImage.getInputStream(), outputStream);
    }
}
