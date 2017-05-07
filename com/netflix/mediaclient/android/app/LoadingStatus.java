// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

public interface LoadingStatus
{
    boolean isLoadingData();
    
    void setLoadingStatusCallback(final LoadingStatus$LoadingStatusCallback p0);
}
