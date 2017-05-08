// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Process;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.IVoip;
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
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.voip.WhistleVoipAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.service.player.PlayerAgent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.mdx.MdxAgent;
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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;

class NetflixService$ResourceFetcherClientCallback extends LoggingResourceFetcherCallback
{
    private final int clientId;
    private final int requestId;
    final /* synthetic */ NetflixService this$0;
    
    NetflixService$ResourceFetcherClientCallback(final NetflixService this$0, final int clientId, final int requestId) {
        this.this$0 = this$0;
        this.clientId = clientId;
        this.requestId = requestId;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        super.onResourceCached(s, s2, n, n2, status);
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onResourceCached");
            return;
        }
        netflixServiceCallback.onResourceCached(this.requestId, s, s2, n, n2, status);
    }
    
    @Override
    public void onResourceFetched(final String s, final String s2, final Status status) {
        super.onResourceFetched(s, s2, status);
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onResourceFetched");
            return;
        }
        netflixServiceCallback.onResourceFetched(this.requestId, s, s2, status);
    }
}
