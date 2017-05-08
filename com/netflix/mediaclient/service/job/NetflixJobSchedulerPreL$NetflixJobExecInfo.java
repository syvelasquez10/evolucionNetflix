// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.job;

import com.google.gson.annotations.SerializedName;

class NetflixJobSchedulerPreL$NetflixJobExecInfo
{
    @SerializedName("lastExecTime")
    public long mLastExecutionTime;
    @SerializedName("job")
    public NetflixJob mNetflixJob;
    @SerializedName("RequestIssueTime")
    public long mRequestIssueTime;
    final /* synthetic */ NetflixJobSchedulerPreL this$0;
    
    NetflixJobSchedulerPreL$NetflixJobExecInfo(final NetflixJobSchedulerPreL this$0) {
        this.this$0 = this$0;
    }
}
