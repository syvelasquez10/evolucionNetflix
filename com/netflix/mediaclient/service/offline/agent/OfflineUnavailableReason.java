// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

public enum OfflineUnavailableReason
{
    NA_DISABLED_FROM_END_POINT(3), 
    NA_MSL_CLIENT_DISABLED(2), 
    NA_OFFLINE_STORAGE_NOT_AVAILABLE(1);
    
    private final int mValue;
    
    private OfflineUnavailableReason(final int mValue) {
        this.mValue = mValue;
    }
    
    public String getCodeForLogblob() {
        return "OF.NA." + this.mValue;
    }
}
