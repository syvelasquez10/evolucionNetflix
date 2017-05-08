// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Process;
import com.netflix.mediaclient.service.job.NetflixJobSchedulerSelector;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.user.UserAgent$UserAgentCallback;
import android.os.SystemClock;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
import android.app.AlarmManager;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.HashMap;
import com.netflix.mediaclient.service.voip.WhistleVoipAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import java.util.Map;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.android.app.Status;
import java.util.ArrayList;
import com.netflix.mediaclient.service.falkor.FalkorAgent;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.error.ErrorAgent;
import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import android.os.IBinder;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.app.Service;
import com.netflix.mediaclient.service.net.DnsManager;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetflixService$7 extends BroadcastReceiver
{
    final /* synthetic */ NetflixService this$0;
    
    NetflixService$7(final NetflixService this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.mNrdController.setNetworkInterfaces();
        this.this$0.mPlayerAgent.handleConnectivityChange(intent);
        this.this$0.mClientLoggingAgent.handleConnectivityChange(intent);
        DnsManager.getInstance().cacheFlush();
    }
}
