// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import java.util.HashMap;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

class NetflixJobSchedulerPreL$PendingJobRegistry
{
    @SerializedName("pendingJobs")
    public final Map<Integer, NetflixJobSchedulerPreL$NetflixJobExecInfo> mNetflixJobs;
    
    NetflixJobSchedulerPreL$PendingJobRegistry() {
        this.mNetflixJobs = new HashMap<Integer, NetflixJobSchedulerPreL$NetflixJobExecInfo>();
    }
}
