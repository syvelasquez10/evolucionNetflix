// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import android.app.Notification;
import com.netflix.mediaclient.service.logging.perf.Events;
import android.os.Process;
import com.netflix.mediaclient.service.player.OfflinePlaybackInterface;
import com.netflix.mediaclient.service.job.NetflixJobSchedulerSelector;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.servicemgr.IVoip;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.IMSLClient;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.servicemgr.IDiagnosis;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.IBrowseInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.servicemgr.IMSLClient$NetworkRequestInspector;
import android.os.SystemClock;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$Trigger;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import java.util.Iterator;
import com.crittercism.app.Crittercism;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.app.PendingIntent;
import android.app.AlarmManager;
import android.content.Intent;
import java.util.HashSet;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.HashMap;
import com.netflix.mediaclient.service.voip.WhistleVoipAgent;
import com.netflix.mediaclient.service.user.UserAgent;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.pushnotification.PushNotificationAgent;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import java.util.Set;
import com.netflix.mediaclient.service.player.PlayerAgent;
import com.netflix.mediaclient.service.pdslogging.PdsAgent;
import com.netflix.mediaclient.service.player.exoplayback.ExoPlayback;
import com.netflix.mediaclient.service.offline.agent.OfflineAgent;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import java.util.Map;
import com.netflix.mediaclient.service.msl.MSLAgent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import java.util.ArrayList;
import com.netflix.mediaclient.service.falkor.FalkorAgent;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.error.ErrorAgent;
import com.netflix.mediaclient.service.diagnostics.DiagnosisAgent;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.media.BookmarkStore;
import android.os.IBinder;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.app.Service;
import com.netflix.model.survey.Survey;
import com.netflix.model.leafs.OnRampEligibility;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.INetflixServiceCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.user.UserAgent$UserAgentCallback;

class NetflixService$UserAgentClientCallback implements UserAgent$UserAgentCallback
{
    private final int clientId;
    private final int requestId;
    final /* synthetic */ NetflixService this$0;
    
    NetflixService$UserAgentClientCallback(final NetflixService this$0, final int clientId, final int requestId) {
        this.this$0 = this$0;
        this.clientId = clientId;
        this.requestId = requestId;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onAutoLoginTokenCreated");
            return;
        }
        Log.d("NetflixService", "Notified onAutoLoginTokenCreated");
        netflixServiceCallback.onAutoLoginTokenCreated(this.requestId, s, status);
    }
    
    @Override
    public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onAvailableAvatarsListFetched");
            return;
        }
        Log.d("NetflixService", "Notified onAvailableAvatarsListFetched");
        netflixServiceCallback.onAvailableAvatarsListFetched(this.requestId, list, status);
    }
    
    @Override
    public void onIrisNotificationsListFetched(final IrisNotificationsList list, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onIrisNotificationsListFetched");
            return;
        }
        Log.d("NetflixService", "Notified onIrisNotificationsListFetched");
        netflixServiceCallback.onIrisNotificationsListFetched(this.requestId, list, status);
    }
    
    @Override
    public void onLoginComplete(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onLoginComplete");
            return;
        }
        Log.d("NetflixService", "Notified onLoginComplete");
        netflixServiceCallback.onLoginComplete(this.requestId, status);
    }
    
    @Override
    public void onLogoutComplete(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onLogoutComplete");
            return;
        }
        Log.d("NetflixService", "Notified onLogoutComplete");
        netflixServiceCallback.onLogoutComplete(this.requestId, status);
    }
    
    @Override
    public void onOnRampEligibilityActionComplete(final OnRampEligibility onRampEligibility, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onOnRampEligibilityActionComplete");
            return;
        }
        Log.d("NetflixService", "Notified onOnRampEligibilityActionComplete");
        netflixServiceCallback.onOnRampEligibilityAction(this.requestId, onRampEligibility, status);
    }
    
    @Override
    public void onProfilesListUpdateResult(final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onProfilesListUpdateResult");
            return;
        }
        Log.d("NetflixService", "Notified onProfilesListUpdateResult");
        netflixServiceCallback.onProfileListUpdateStatus(this.requestId, status);
    }
    
    @Override
    public void onSurveyFetched(final Survey survey, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onSurveyFetched");
            return;
        }
        Log.d("NetflixService", "Notified onSurveyFetched");
        netflixServiceCallback.onSurveyFetched(this.requestId, survey, status);
    }
    
    @Override
    public void onVerified(final boolean b, final Status status) {
        final INetflixServiceCallback netflixServiceCallback = (INetflixServiceCallback)this.this$0.mClientCallbacks.get(this.clientId);
        if (netflixServiceCallback == null) {
            Log.w("NetflixService", "No client callback found for onVerified");
            return;
        }
        Log.d("NetflixService", "Notified onVerified");
        netflixServiceCallback.onVerified(this.requestId, b, status);
    }
}
