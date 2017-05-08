// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Map;

public abstract class BaseNetworkFetcher<FETCH_STATE extends FetchState> implements NetworkFetcher<FETCH_STATE>
{
    @Override
    public Map<String, String> getExtraMap(final FETCH_STATE fetch_STATE, final int n) {
        return null;
    }
    
    @Override
    public void onFetchCompletion(final FETCH_STATE fetch_STATE, final int n) {
    }
    
    @Override
    public boolean shouldPropagate(final FETCH_STATE fetch_STATE) {
        return true;
    }
}
