// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;

public class LoggingResourceFetcherCallback implements ResourceFetcherCallback
{
    private static final String TAG = "nf_service_resfetcher_callback";
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        if (Log.isLoggable()) {
            Log.i("nf_service_resfetcher_callback", String.format("Resource %s fetched and saved at %s, status %d", s, s2, status.getStatusCode().getValue()));
        }
    }
    
    @Override
    public void onResourcePrefetched(final String s, final int n, final Status status) {
        if (Log.isLoggable()) {
            Log.d("nf_service_resfetcher_callback", String.format("Resource %s prefetched, status %d", s, status.getStatusCode().getValue()));
        }
        if (status.isError() && Log.isLoggable()) {
            Log.w("nf_service_resfetcher_callback", String.format("Resource %s could not be prefetched, status %d", s, status.getStatusCode().getValue()));
        }
    }
}
