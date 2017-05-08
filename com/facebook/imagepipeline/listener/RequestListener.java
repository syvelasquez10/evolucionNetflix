// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.listener;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.producers.ProducerListener;

public interface RequestListener extends ProducerListener
{
    void onRequestCancellation(final String p0);
    
    void onRequestFailure(final ImageRequest p0, final String p1, final Throwable p2, final boolean p3);
    
    void onRequestStart(final ImageRequest p0, final Object p1, final String p2, final boolean p3);
    
    void onRequestSuccess(final ImageRequest p0, final String p1, final boolean p2);
}
