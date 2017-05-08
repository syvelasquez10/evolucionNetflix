// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

public interface NetflixJobExecutor
{
    void onNetflixStartJob(final NetflixJob$NetflixJobId p0);
    
    void onNetflixStopJob(final NetflixJob$NetflixJobId p0);
}
