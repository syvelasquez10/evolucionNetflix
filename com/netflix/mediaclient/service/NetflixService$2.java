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
import android.content.Context;
import android.app.PendingIntent;
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.servicemgr.SignUpParams;

class NetflixService$2 implements SignUpParams
{
    final /* synthetic */ NetflixService this$0;
    
    NetflixService$2(final NetflixService this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public String getSignUpBootloader() {
        return this.this$0.mConfigurationAgent.getSignUpConfiguration().getSignUpBootloader();
    }
    
    @Override
    public long getSignUpTimeout() {
        return this.this$0.mConfigurationAgent.getSignUpConfiguration().getSignUpTimeout();
    }
    
    @Override
    public boolean isSignUpEnabled() {
        return this.this$0.mConfigurationAgent.getSignUpConfiguration().isSignEnabled();
    }
}
