// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.javabridge.ui.Log;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ClientStatsLogHandler;

public class ClientStatsLogHandlerImpl implements ClientStatsLogHandler
{
    private static final String LOG_TAG = "nf_clientstats";
    private static final String MULTIHISTOGRAM_LOGTYPE = "multihistogram";
    private static final int RECURRENCE_INTERVAL_MILLIS;
    private final Context mContext;
    ScheduledExecutorService mExecutor;
    
    static {
        RECURRENCE_INTERVAL_MILLIS = (int)TimeUnit.MINUTES.toMillis(30L);
    }
    
    public ClientStatsLogHandlerImpl(final Context mContext) {
        this.mContext = mContext;
    }
    
    @Override
    public void init(final ScheduledExecutorService mExecutor, final Log log) {
        (this.mExecutor = mExecutor).scheduleAtFixedRate(new ClientStatsLogHandlerImpl$1(this, log), ClientStatsLogHandlerImpl.RECURRENCE_INTERVAL_MILLIS, ClientStatsLogHandlerImpl.RECURRENCE_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
    }
}
