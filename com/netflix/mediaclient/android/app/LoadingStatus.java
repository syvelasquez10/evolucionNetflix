// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

public interface LoadingStatus
{
    boolean isLoadingData();
    
    void setLoadingStatusCallback(final LoadingStatusCallback p0);
    
    public interface LoadingStatusCallback
    {
        void onDataLoaded(final int p0);
    }
}
