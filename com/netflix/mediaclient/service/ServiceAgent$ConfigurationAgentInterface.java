// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

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

public interface ServiceAgent$ConfigurationAgentInterface
{
    void clearAccountConfigData();
    
    boolean enableHTTPSAuth();
    
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
