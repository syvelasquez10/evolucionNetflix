// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.media.VideoResolutionRange;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration;
import org.json.JSONObject;
import com.netflix.mediaclient.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import org.json.JSONArray;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;

public abstract class ServiceAgent
{
    private static final String TAG = "nf_service_ServiceAgent";
    private AgentContext agentContext;
    private InitCallback initCallback;
    private boolean initCalled;
    private Status initErrorResult;
    private Handler mainHandler;
    
    public ServiceAgent() {
        this.initErrorResult = CommonStatus.UNKNOWN;
    }
    
    public void destroy() {
        Log.d("nf_service_ServiceAgent", "Destroying " + this.getClass().getSimpleName());
        this.agentContext = null;
    }
    
    protected abstract void doInit();
    
    public NetflixApplication getApplication() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getApplication();
        }
        return null;
    }
    
    public BrowseAgentInterface getBrowseAgent() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getBrowseAgent();
        }
        return null;
    }
    
    public ConfigurationAgentInterface getConfigurationAgent() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getConfigurationAgent();
        }
        return null;
    }
    
    protected Context getContext() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return (Context)agentContext.getService();
        }
        return null;
    }
    
    public Handler getMainHandler() {
        return this.mainHandler;
    }
    
    public NrdController getNrdController() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getNrdController();
        }
        return null;
    }
    
    public PreAppAgentInterface getPreAppAgent() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getPreAppAgent();
        }
        return null;
    }
    
    public ResourceFetcher getResourceFetcher() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getResourceFetcher();
        }
        return null;
    }
    
    protected NetflixService getService() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getService();
        }
        return null;
    }
    
    protected UserAgentInterface getUserAgent() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getUserAgent();
        }
        return null;
    }
    
    public final void init(final AgentContext agentContext, final InitCallback initCallback) {
        synchronized (this) {
            ThreadUtils.assertOnMain();
            Log.d("nf_service_ServiceAgent", "Request to init " + this.getClass().getSimpleName());
            if (this.initCalled) {
                throw new IllegalStateException("ServiceAgent init already called");
            }
        }
        final AgentContext agentContext2;
        if (agentContext2 == null) {
            throw new NullPointerException("AgentContext can not be null");
        }
        this.agentContext = agentContext2;
        this.initCalled = true;
        this.initCallback = initCallback;
        this.mainHandler = new Handler();
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("nf_service_ServiceAgent", "Initing " + ServiceAgent.this.getClass().getSimpleName());
                ServiceAgent.this.doInit();
            }
        });
    }
    // monitorexit(this)
    
    protected final void initCompleted(final Status initErrorResult) {
        synchronized (this) {
            this.initErrorResult = initErrorResult;
            if (Log.isLoggable("nf_service_ServiceAgent", 3)) {
                Log.d("nf_service_ServiceAgent", "InitComplete with errorCode " + this.initErrorResult + " for " + this.getClass().getSimpleName());
            }
            if (this.initCallback != null) {
                this.mainHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        ServiceAgent.this.initCallback.onInitComplete(ServiceAgent.this, ServiceAgent.this.initErrorResult);
                    }
                });
            }
        }
    }
    
    public boolean isReady() {
        synchronized (this) {
            return this.initErrorResult.isSucces();
        }
    }
    
    public interface AgentContext
    {
        NetflixApplication getApplication();
        
        BrowseAgentInterface getBrowseAgent();
        
        ConfigurationAgentInterface getConfigurationAgent();
        
        NrdController getNrdController();
        
        PreAppAgentInterface getPreAppAgent();
        
        ResourceFetcher getResourceFetcher();
        
        NetflixService getService();
        
        UserAgentInterface getUserAgent();
    }
    
    public interface BrowseAgentInterface
    {
        List<Billboard> fetchBillboardsFromCache(final int p0);
        
        List<CWVideo> fetchCWFromCache(final int p0);
        
        void fetchEpisodeDetails(final String p0, final BrowseAgentCallback p1);
        
        List<Video> fetchIQFromCache(final int p0);
        
        void fetchMovieDetails(final String p0, final BrowseAgentCallback p1);
        
        void fetchPostPlayVideos(final String p0, final BrowseAgentCallback p1);
        
        List<Video> fetchRecommendedListFromCache(final int p0);
        
        void fetchSeasonDetails(final String p0, final BrowseAgentCallback p1);
        
        void fetchShowDetails(final String p0, final String p1, final BrowseAgentCallback p2);
    }
    
    public interface ConfigurationAgentInterface
    {
        void clearAccountConfigData();
        
        void esnMigrationComplete();
        
        void fetchAccountConfigData(final ConfigurationAgentWebCallback p0);
        
        ApiEndpointRegistry getApiEndpointRegistry();
        
        int getApmUserSessionDurationInSeconds();
        
        int getAppVersionCode();
        
        BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification();
        
        JSONArray getCastWhiteList();
        
        ConsolidatedLoggingSessionSpecification getConsolidatedLoggingSessionSpecification(final String p0);
        
        PlayerType getCurrentPlayerType();
        
        int getDataRequestTimeout();
        
        DeviceCategory getDeviceCategory();
        
        int getDiskCacheSizeBytes();
        
        DrmManager getDrmManager();
        
        ErrorLoggingSpecification getErrorLoggingSpecification();
        
        EsnProvider getEsnProvider();
        
        long getImageCacheMinimumTtl();
        
        int getImageCacheSizeBytes();
        
        IpConnectivityPolicy getIpConnectivityPolicy();
        
        JSONObject getJPlayerConfig();
        
        KidsOnPhoneConfiguration getKidsOnPhoneConfiguration();
        
        JSONArray getMdxBlackListTargets();
        
        int getPresentationTrackingAggregationSize();
        
        int getRateLimitForGcmBrowseEvents();
        
        int getRateLimitForNListChangeEvents();
        
        int getResFetcherThreadPoolSize();
        
        int getResourceRequestTimeout();
        
        int getSearchTest();
        
        String getSoftwareVersion();
        
        String getStreamingQoe();
        
        SubtitleConfiguration getSubtitleConfiguration();
        
        int getVideoBufferSize();
        
        VideoResolutionRange getVideoResolutionRange();
        
        boolean isCurrentDrmWidevine();
        
        boolean isDeviceLowMem();
        
        boolean isDisableMdx();
        
        boolean isDisableWebsocket();
        
        boolean isDisableWidevine();
        
        boolean isEnableCast();
        
        boolean isEsnMigrationRequired();
        
        boolean isLogoutRequired();
        
        boolean toDisableSuspendPlayback();
        
        void userAgentLogoutComplete();
    }
    
    public interface InitCallback
    {
        void onInitComplete(final ServiceAgent p0, final Status p1);
    }
    
    public interface PreAppAgentInterface
    {
        boolean isWidgetInstalled();
    }
    
    public interface UserAgentInterface
    {
        void doDummyWebCall(final UserAgentWebCallback p0);
        
        UserProfile getCurrentProfile();
        
        String getCurrentProfileGuid();
        
        String getGeoCountry();
        
        String getLanguagesInCsv();
        
        String getReqCountry();
        
        TextStyle getSubtitleDefaults();
        
        UserCredentialRegistry getUserCredentialRegistry();
        
        TextStyle getUserSubtitlePreferences();
        
        boolean isCurrentProfileIQEnabled();
        
        boolean isProfileSwitchingDisabled();
        
        boolean isUserLoggedIn();
        
        void logoutUser();
        
        void refreshProfileSwitchingStatus();
    }
}
