// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.servicemgr.UserProfile;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.media.bitrate.VideoBitrateRange;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import org.json.JSONArray;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import android.content.Context;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.Log;
import android.os.Handler;

public abstract class ServiceAgent
{
    private static final String TAG = "nf_service_ServiceAgent";
    private AgentContext agentContext;
    private InitCallback initCallback;
    private boolean initCalled;
    private int initErrorCode;
    private Handler mainHandler;
    
    public ServiceAgent() {
        this.initErrorCode = -1;
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
    
    protected BrowseAgentInterface getBrowseAgent() {
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
    
    protected Handler getMainHandler() {
        return this.mainHandler;
    }
    
    public NrdController getNrdController() {
        final AgentContext agentContext = this.agentContext;
        if (agentContext != null) {
            return agentContext.getNrdController();
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
    
    protected final void initCompleted(final int initErrorCode) {
        synchronized (this) {
            this.initErrorCode = initErrorCode;
            Log.d("nf_service_ServiceAgent", "InitComplete with errorCode " + this.initErrorCode + " for " + this.getClass().getSimpleName());
            if (this.initCallback != null) {
                this.mainHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        ServiceAgent.this.initCallback.onInitComplete(ServiceAgent.this, ServiceAgent.this.initErrorCode);
                    }
                });
            }
        }
    }
    
    public boolean isReady() {
        synchronized (this) {
            return this.initErrorCode == 0;
        }
    }
    
    public interface AgentContext
    {
        NetflixApplication getApplication();
        
        BrowseAgentInterface getBrowseAgent();
        
        ConfigurationAgentInterface getConfigurationAgent();
        
        NrdController getNrdController();
        
        ResourceFetcher getResourceFetcher();
        
        NetflixService getService();
        
        UserAgentInterface getUserAgent();
    }
    
    public interface BrowseAgentInterface
    {
        void fetchEpisodeDetails(final String p0, final BrowseAgentCallback p1);
        
        void fetchMovieDetails(final String p0, final BrowseAgentCallback p1);
        
        void fetchSeasonDetails(final String p0, final BrowseAgentCallback p1);
        
        void fetchShowDetails(final String p0, final String p1, final BrowseAgentCallback p2);
    }
    
    public interface ConfigurationAgentInterface
    {
        void clearAccountConfigData();
        
        void esnMigrationComplete();
        
        void fetchAccountConfigData(final ConfigurationAgentWebCallback p0);
        
        ApiEndpointRegistry getApiEndpointRegistry();
        
        int getAppVersionCode();
        
        int getBitrateCap();
        
        ConsolidatedLoggingSessionSpecification getConsolidatedLoggingSessionSpecification(final String p0);
        
        PlayerType getCurrentPlayerType();
        
        int getDataRequestTimeout();
        
        DeviceCategory getDeviceCategory();
        
        int getDiskCacheSizeBytes();
        
        DrmManager getDrmManager();
        
        EsnProvider getEsnProvider();
        
        long getImageCacheMinimumTtl();
        
        int getImageCacheSizeBytes();
        
        JSONArray getMdxBlackListTargets();
        
        int getResFetcherThreadPoolSize();
        
        int getResourceRequestTimeout();
        
        String getSoftwareVersion();
        
        String getStreamingQoe();
        
        SubtitleConfiguration getSubtitleConfiguration();
        
        VideoBitrateRange[] getVideoBitrateRange();
        
        int getVideoBufferSize();
        
        boolean isDeviceLowMem();
        
        boolean isDisableMdxFlagSet();
        
        boolean isDisableWebsocketFlagSet();
        
        boolean isDisableWidevineFlagSet();
        
        boolean isEsnMigrationRequired();
        
        boolean isLogoutRequired();
        
        void userAgentLogoutComplete();
    }
    
    public interface InitCallback
    {
        void onInitComplete(final ServiceAgent p0, final int p1);
    }
    
    public interface UserAgentInterface
    {
        void doDummyWebCall(final UserAgentWebCallback p0);
        
        UserProfile getCurrentProfile();
        
        String getCurrentProfileId();
        
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
