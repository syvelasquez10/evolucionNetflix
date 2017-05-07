// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.media.VideoResolutionRange;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.service.configuration.PlaybackConfiguration;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import com.netflix.mediaclient.service.configuration.KubrickConfiguration;
import org.json.JSONObject;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;

public interface ServiceAgent$ConfigurationAgentInterface
{
    void clearAccountConfigData();
    
    boolean enableHTTPSAuth();
    
    boolean enableLowBitrateStreams();
    
    void esnMigrationComplete();
    
    void fetchAccountConfigData(final ConfigurationAgentWebCallback p0);
    
    ABTestConfigData getABTestConfiguration_6538();
    
    ABTestConfigData getABTestConfiguration_6725();
    
    String getAlertMsgForMissingLocale();
    
    ApiEndpointRegistry getApiEndpointRegistry();
    
    int getApmUserSessionDurationInSeconds();
    
    int getAppVersionCode();
    
    DataSaveConfigData getBWSaveConfigData();
    
    BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification();
    
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
    
    int getJPlayerStreamErrorRestartCount();
    
    KubrickConfiguration getKubrickConfiguration();
    
    MdxConfiguration getMdxConfiguration();
    
    PlaybackConfiguration getPlaybackConfiguration();
    
    String getPreAppPartnerExperience();
    
    String getPreAppWidgetExperience();
    
    int getPresentationTrackingAggregationSize();
    
    int getRateLimitForGcmBrowseEvents();
    
    int getRateLimitForNListChangeEvents();
    
    int getResFetcherThreadPoolSize();
    
    int getResourceRequestTimeout();
    
    String getSoftwareVersion();
    
    String getStreamingQoe();
    
    SubtitleConfiguration getSubtitleConfiguration();
    
    int getVideoBufferSize();
    
    VideoResolutionRange getVideoResolutionRange();
    
    int getVoipSampleRate();
    
    boolean isCurrentDrmWidevine();
    
    boolean isDeviceLowMem();
    
    boolean isDisableMcQueenV2();
    
    boolean isDolbyDigitalPlus51Supported();
    
    boolean isEsnMigrationRequired();
    
    boolean isLogoutRequired();
    
    boolean isWidevineL1Enabled();
    
    boolean isWidevineL3ABTestEnabled();
    
    boolean isWidevineL3Enabled();
    
    boolean shouldAlertForMissingLocale();
    
    boolean shouldDisableVoip();
    
    boolean shouldForceBwSettingsInWifi();
    
    void userAgentLogoutComplete();
}
