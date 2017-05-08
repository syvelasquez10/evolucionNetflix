// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ClientStatsLogHandler;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.javabridge.ui.LogArguments$LogLevel;
import com.netflix.mediaclient.util.ConnectivityUtils$NetType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.cstatssamurai.ClientStats;
import com.netflix.mediaclient.javabridge.ui.Log;

class ClientStatsLogHandlerImpl$1 implements Runnable
{
    final /* synthetic */ ClientStatsLogHandlerImpl this$0;
    final /* synthetic */ Log val$nrdpLog;
    
    ClientStatsLogHandlerImpl$1(final ClientStatsLogHandlerImpl this$0, final Log val$nrdpLog) {
        this.this$0 = this$0;
        this.val$nrdpLog = val$nrdpLog;
    }
    
    @Override
    public void run() {
        try {
            ClientStats.getInstance().takeSnapshot();
            if (ConnectivityUtils.getCurrentNetType(this.this$0.mContext) == ConnectivityUtils$NetType.wifi) {
                final LogArguments logArguments = new LogArguments(LogArguments$LogLevel.INFO, ClientStats.getInstance().getJournal(), "multihistogram", null);
                com.netflix.mediaclient.Log.d("nf_clientstats", "Sending log");
                this.val$nrdpLog.log(logArguments);
                ClientStats.getInstance().reset();
            }
        }
        catch (NullPointerException ex) {
            com.netflix.mediaclient.Log.e("nf_clientstats", "problem sending log", ex);
        }
    }
}
