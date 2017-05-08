// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

public enum DownloadState
{
    Complete(4), 
    CreateFailed(7), 
    Creating(1), 
    DeleteComplete(6), 
    Deleted(5), 
    InProgress(2), 
    Stopped(3), 
    Unknown(0);
    
    private final int mValue;
    
    private DownloadState(final int mValue) {
        this.mValue = mValue;
    }
    
    public static DownloadState getStateByValue(final int n) {
        final DownloadState[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final DownloadState downloadState = values[i];
            if (downloadState.getIntValue() == n) {
                return downloadState;
            }
        }
        return DownloadState.Unknown;
    }
    
    public int getIntValue() {
        return this.mValue;
    }
}
