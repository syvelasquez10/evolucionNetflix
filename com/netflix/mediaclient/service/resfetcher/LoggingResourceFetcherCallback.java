// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.Log;

public class LoggingResourceFetcherCallback implements ResourceFetcherCallback
{
    private static final String TAG = "nf_service_resfetcher_callback";
    
    @Override
    public void onResourceFetched(final String s, final String s2, final int n) {
        Log.i("nf_service_resfetcher_callback", String.format("Resource %s fetched and saved at %s, status %d", s, s2, n));
    }
    
    @Override
    public void onResourcePrefetched(final String s, final int n, final int n2) {
        if (Log.isLoggable("nf_service_resfetcher_callback", 3)) {
            Log.d("nf_service_resfetcher_callback", String.format("Resource %s prefetched, status %d", s, n2));
        }
        if (n2 != 0) {
            Log.w("nf_service_resfetcher_callback", String.format("Resource %s could not be prefetched, status %d", s, n2));
        }
    }
}
