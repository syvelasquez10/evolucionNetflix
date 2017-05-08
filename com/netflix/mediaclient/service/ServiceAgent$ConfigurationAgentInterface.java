// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.webclient.model.leafs.NrmLanguagesData;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipConfiguration;
import com.netflix.mediaclient.media.VideoResolutionRange;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitleDownloadRetryPolicy;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.PreviewContentConfigData;
import com.netflix.mediaclient.service.configuration.PlaybackConfiguration;
import com.netflix.mediaclient.service.webclient.model.leafs.OfflineConfig;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import com.netflix.mediaclient.service.configuration.MdxConfiguration;
import org.json.JSONObject;
import com.netflix.mediaclient.service.net.IpConnectivityPolicy;
import com.netflix.mediaclient.service.configuration.ImageResolutionClass;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.service.webclient.model.leafs.ErrorLoggingSpecification;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.service.configuration.DeviceModel;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.webclient.model.leafs.ConsolidatedLoggingSessionSpecification;
import android.util.Pair;
import com.netflix.mediaclient.service.webclient.model.leafs.BreadcrumbLoggingSpecification;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;

public interface ServiceAgent$ConfigurationAgentInterface
{
    void allocateABTest(final int p0, final int p1, final ConfigurationAgentWebCallback p2);
    
    void clearAccountConfigData();
    
    boolean enableHTTPSAuth();
    
    boolean enableLowBitrateStreams();
    
    void esnMigrationComplete();
    
    void fetchAccountConfigData(final ConfigurationAgentWebCallback p0);
    
    ABTestConfigData getABTestConfig();
    
    String getAlertMsgForMissingLocale();
    
    ApiEndpointRegistry getApiEndpointRegistry();
    
    int getApmUserSessionDurationInSeconds();
    
    int getAppVersionCode();
    
    DataSaveConfigData getBWSaveConfigData();
    
    BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification();
    
    Pair<String, byte[]> getCastPrefetchSharedSecret();
    
    String getChannelId();
    
    ConsolidatedLoggingSessionSpecification getConsolidatedLoggingSessionSpecification(final String p0);
    
    PlayerType getCurrentPlayerType();
    
    int getDataRequestThreadPoolSize();
    
    int getDataRequestTimeout();
    
    DeviceCategory getDeviceCategory();
    
    DeviceModel getDeviceModel();
    
    int getDiskCacheSizeBytes();
    
    int getDownloadAgentThreadPoolSize();
    
    DrmManager getDrmManager();
    
    ErrorLoggingSpecification getErrorLoggingSpecification();
    
    EsnProvider getEsnProvider();
    
    String getGeoCountryCode();
    
    long getImageCacheMinimumTtl();
    
    int getImageCacheSizeBytes();
    
    String getImagePreference();
    
    ImageResolutionClass getImageResolutionClass();
    
    IpConnectivityPolicy getIpConnectivityPolicy();
    
    JSONObject getJPlayerConfig();
    
    int getJPlayerStreamErrorRestartCount();
    
    MdxConfiguration getMdxConfiguration();
    
    NrmConfigData getNrmConfigData();
    
    OfflineConfig getOfflineConfig();
    
    PlaybackConfiguration getPlaybackConfiguration();
    
    String getPreAppPartnerExperience();
    
    String getPreAppWidgetExperience();
    
    int getPresentationTrackingAggregationSize();
    
    PreviewContentConfigData getPreviewContentConfiguration();
    
    int getRateLimitForGcmBrowseEvents();
    
    int getRateLimitForNListChangeEvents();
    
    int getResFetcherThreadPoolSize();
    
    int getResourceRequestTimeout();
    
    SignInConfigData getSignInConfigData();
    
    String getSoftwareVersion();
    
    String getStreamingQoe();
    
    SubtitleConfiguration getSubtitleConfiguration();
    
    SubtitleDownloadRetryPolicy getSubtitleDownloadRetryPolicy();
    
    String getUserPin();
    
    int getVideoBufferSize();
    
    VideoResolutionRange getVideoResolutionRange();
    
    VoipConfiguration getVoipConfiguration();
    
    boolean ignorePreloadForPlayBilling();
    
    boolean isActivityTrackingDisabled();
    
    boolean isAllowHevcMobile();
    
    boolean isAllowVp9Mobile();
    
    boolean isAssistiveAudioEnabled();
    
    boolean isCurrentDrmWidevine();
    
    boolean isDeviceHd();
    
    boolean isDeviceLowMem();
    
    boolean isDisableCastFaststart();
    
    boolean isDisableMcQueenV2();
    
    boolean isDolbyDigitalPlus20Supported();
    
    boolean isDolbyDigitalPlus51Supported();
    
    boolean isDynecomSignInEnabled();
    
    boolean isEsnMigrationRequired();
    
    boolean isLogoutRequired();
    
    boolean isMementoEnabledForWorld();
    
    boolean isPlayBillingDisabled();
    
    boolean isPreviewContentEnabled();
    
    boolean isWidevineL1Enabled();
    
    void persistNrmConfigData(final NrmConfigData p0);
    
    void persistNrmLanguagesData(final NrmLanguagesData p0);
    
    void setShouldUseAndroidHttpStack(final boolean p0);
    
    boolean shouldAlertForMissingLocale();
    
    boolean shouldDisableVoip();
    
    boolean shouldForceLegacyCrypto();
    
    boolean shouldUseAndroidHttpStack();
    
    boolean showHelpForNonMemebers();
    
    void userAgentLogoutComplete();
    
    void verifyLoginViaDynecom(final String p0, final String p1, final ConfigurationAgentWebCallback p2);
}
