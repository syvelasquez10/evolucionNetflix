// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

public interface NetflixJobScheduler
{
    void cancelJob(final NetflixJob$NetflixJobId p0);
    
    boolean isJobScheduled(final NetflixJob$NetflixJobId p0);
    
    void onJobFinished(final NetflixJob$NetflixJobId p0, final boolean p1);
    
    void scheduleJob(final NetflixJob p0);
}
