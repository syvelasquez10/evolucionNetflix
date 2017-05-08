// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;

public interface NetflixService$JobExecutionMonitor
{
    void onJobStarted(final NetflixJob$NetflixJobId p0);
    
    void onJobStopped(final NetflixJob$NetflixJobId p0);
}
