// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequest;

public class SettableProducerContext extends BaseProducerContext
{
    public SettableProducerContext(final ImageRequest imageRequest, final String s, final ProducerListener producerListener, final Object o, final ImageRequest$RequestLevel imageRequest$RequestLevel, final boolean b, final boolean b2, final Priority priority) {
        super(imageRequest, s, producerListener, o, imageRequest$RequestLevel, b, b2, priority);
    }
}
