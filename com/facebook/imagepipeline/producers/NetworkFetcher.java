// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Map;
import com.facebook.imagepipeline.image.EncodedImage;

public interface NetworkFetcher<FETCH_STATE extends FetchState>
{
    FETCH_STATE createFetchState(final Consumer<EncodedImage> p0, final ProducerContext p1);
    
    void fetch(final FETCH_STATE p0, final NetworkFetcher$Callback p1);
    
    Map<String, String> getExtraMap(final FETCH_STATE p0, final int p1);
    
    void onFetchCompletion(final FETCH_STATE p0, final int p1);
    
    boolean shouldPropagate(final FETCH_STATE p0);
}
