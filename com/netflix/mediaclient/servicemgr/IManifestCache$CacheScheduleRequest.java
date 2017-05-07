// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public class IManifestCache$CacheScheduleRequest
{
    private long mMovieId;
    private long mPriority;
    private long mTrackId;
    
    public IManifestCache$CacheScheduleRequest(final long mMovieId, final long mTrackId, final long mPriority) {
        this.mMovieId = mMovieId;
        this.mTrackId = mTrackId;
        this.mPriority = mPriority;
    }
    
    public long getMovieId() {
        return this.mMovieId;
    }
    
    public long getPriority() {
        return this.mPriority;
    }
    
    public long getTrackId() {
        return this.mTrackId;
    }
}
