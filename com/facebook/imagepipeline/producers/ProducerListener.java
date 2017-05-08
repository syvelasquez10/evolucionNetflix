// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Map;

public interface ProducerListener
{
    void onProducerEvent(final String p0, final String p1, final String p2);
    
    void onProducerFinishWithCancellation(final String p0, final String p1, final Map<String, String> p2);
    
    void onProducerFinishWithFailure(final String p0, final String p1, final Throwable p2, final Map<String, String> p3);
    
    void onProducerFinishWithSuccess(final String p0, final String p1, final Map<String, String> p2);
    
    void onProducerStart(final String p0, final String p1);
    
    boolean requiresExtraMap(final String p0);
}
