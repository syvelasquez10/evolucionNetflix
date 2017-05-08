// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.ui.Log;
import java.util.concurrent.ScheduledExecutorService;

public interface ClientStatsLogHandler
{
    public static final String LOG_TAG = "nf_clientstats";
    public static final String MULTIHISTOGRAM_LOGTYPE = "multihistogram";
    
    void init(final ScheduledExecutorService p0, final Log p1);
}
