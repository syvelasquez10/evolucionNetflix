// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;

public interface ThumbnailProducer<T> extends Producer<T>
{
    boolean canProvideImageForSize(final ResizeOptions p0);
}
