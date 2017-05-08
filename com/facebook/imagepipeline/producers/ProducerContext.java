// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequest;

public interface ProducerContext
{
    void addCallbacks(final ProducerContextCallbacks p0);
    
    Object getCallerContext();
    
    String getId();
    
    ImageRequest getImageRequest();
    
    ProducerListener getListener();
    
    ImageRequest$RequestLevel getLowestPermittedRequestLevel();
    
    Priority getPriority();
    
    boolean isIntermediateResultExpected();
    
    boolean isPrefetch();
}
