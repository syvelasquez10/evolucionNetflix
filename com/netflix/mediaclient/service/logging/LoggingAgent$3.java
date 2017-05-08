// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.PresentationTracking;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.SignInLogging;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging;
import com.netflix.mediaclient.servicemgr.ISearchLogging;
import com.netflix.mediaclient.servicemgr.UIViewLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.error.CrittercismErrorLoggingImpl;
import com.netflix.mediaclient.service.logging.breadcrumb.CrittercismBreadcrumbLoggingImpl;
import android.os.HandlerThread;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.CustomerEventLogging;
import com.netflix.mediaclient.servicemgr.CmpEventLogging;
import com.netflix.mediaclient.servicemgr.BreadcrumbLogging;
import com.netflix.mediaclient.service.logging.ads.AdvertiserIdLoggingManager;
import java.util.concurrent.ThreadFactory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent$ConfigAgentListener;
import com.netflix.mediaclient.javabridge.ui.Log$AppIdChangedListener;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class LoggingAgent$3 extends BroadcastReceiver
{
    final /* synthetic */ LoggingAgent this$0;
    
    LoggingAgent$3(final LoggingAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.v("nf_log", "Received intent " + intent);
        }
        this.this$0.mIntegratedClientLoggingManager.handleIntent(intent);
    }
}
