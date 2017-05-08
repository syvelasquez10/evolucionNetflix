// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

public enum NetflixJob$NetflixJobId
{
    DOWNLOAD_MAINTENANCE(2), 
    DOWNLOAD_RESUME(1), 
    FALKOR_METADATA(3), 
    UNKNOWN_JOB_ID(-1);
    
    private final int mValue;
    
    private NetflixJob$NetflixJobId(final int mValue) {
        this.mValue = mValue;
    }
    
    public static NetflixJob$NetflixJobId getJobIdByValue(final int n) {
        final NetflixJob$NetflixJobId[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final NetflixJob$NetflixJobId netflixJob$NetflixJobId = values[i];
            if (netflixJob$NetflixJobId.getIntValue() == n) {
                return netflixJob$NetflixJobId;
            }
        }
        return NetflixJob$NetflixJobId.UNKNOWN_JOB_ID;
    }
    
    public int getIntValue() {
        return this.mValue;
    }
}
