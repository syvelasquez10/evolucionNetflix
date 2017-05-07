// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

public interface ResourceFetcherCallback
{
    void onResourceFetched(final String p0, final String p1, final int p2);
    
    void onResourcePrefetched(final String p0, final int p1, final int p2);
}
