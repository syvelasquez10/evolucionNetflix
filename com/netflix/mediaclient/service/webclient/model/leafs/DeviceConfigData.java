// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeviceConfigData
{
    @SerializedName("alertMsgForLocaleSupport")
    private String alertMsgForLocaleSupport;
    @SerializedName("appBootUrlSuffix")
    private String appBootUrlSuffix;
    @SerializedName("audioFormats")
    private int audioFormats;
    @SerializedName("breadcrumb_logging_specification")
    private BreadcrumbLoggingSpecification breadcrumb_logging_specification;
    @SerializedName("consolidated_logging_specification")
    private List<ConsolidatedLoggingSessionSpecification> consolidated_logging_specification;
    private String current_version;
    private String device_category;
    @SerializedName("disableCastFaststart")
    private boolean disableCastFaststart;
    @SerializedName("disableDataSaver")
    private boolean disableDataSaver;
    @SerializedName("disablePlayBilling")
    private boolean disablePlayBilling;
    private String disable_mdx;
    private String disable_websocket;
    @SerializedName("enableDynecomSignIn")
    private boolean enableDynecomSignIn;
    @SerializedName("enableLocalPlayback")
    private String enableLocalPlayback;
    @SerializedName("enableMdxRemoteControlLockScreen")
    private String enableMdxRemoteControlLockScreen;
    @SerializedName("enableMdxRemoteControlNotification")
    private String enableMdxRemoteControlNotification;
    @SerializedName("enableWidevineL1")
    private boolean enableWidevineL1;
    @SerializedName("error_logging_specification")
    private ErrorLoggingSpecification error_logging_specification;
    @SerializedName("forceLegacyCrypto")
    private boolean forceLegacyCrypto;
    @SerializedName("gcmBrowseEventRateLimitInSecs")
    private int gcmBrowseEventRateLimit;
    @SerializedName("gcmNListChangeEventRateLimitInSecs")
    private int gcmNListChangeEventRateLimit;
    @SerializedName("ignorePreloadForPlayBilling")
    private boolean ignorePreloadForPlayBilling;
    private String image_pref;
    @SerializedName("ip_connectivity_policy")
    private int ip_connectivity_policy;
    @SerializedName("jPlayerRestartOnStreamErrors")
    private int jPlayerRestartOnStreamErrors;
    private String min_version;
    private String pt_aggregation_size;
    @SerializedName("shouldAlertForLocaleSupport")
    private boolean shouldAlertForLocaleSupport;
    private String signup_enabled;
    private String signup_timeout;
    @SerializedName("subtitleDownloadRetryPolicy")
    private SubtitleDownloadRetryPolicy subtitleDownloadRetryPolicy;
    private String subtitle_configuration;
    private String type;
    @SerializedName("user_session_timeout_duration")
    private int user_session_timeout_duration;
    @SerializedName("videoResolutionOverride")
    private int videoResolutionOverride;
    @SerializedName("voipConfig")
    private VoipConfiguration voipConfig;
    @SerializedName("voipConfirmationDialogAllocationPercentage")
    private int voipConfirmationDialogAllocationPercentage;
    @SerializedName("voipEnabledOnDevice")
    private boolean voipEnabledOnDevice;
    
    public DeviceConfigData() {
        this.consolidated_logging_specification = new ArrayList<ConsolidatedLoggingSessionSpecification>();
        this.ip_connectivity_policy = -1;
        this.user_session_timeout_duration = -1;
        this.jPlayerRestartOnStreamErrors = 2;
        this.voipConfirmationDialogAllocationPercentage = 25;
    }
    
    public String getAlertMsgForLocaleSupport() {
        return this.alertMsgForLocaleSupport;
    }
    
    public String getAppBootUrlSuffix() {
        return this.appBootUrlSuffix;
    }
    
    public String getAppMinVresion() {
        return this.min_version;
    }
    
    public String getAppRecommendedVresion() {
        return this.current_version;
    }
    
    public int getAudioFormats() {
        return this.audioFormats;
    }
    
    public BreadcrumbLoggingSpecification getBreadcrumbLoggingSpecification() {
        return this.breadcrumb_logging_specification;
    }
    
    public List<ConsolidatedLoggingSessionSpecification> getConsolidatedloggingSpecification() {
        return this.consolidated_logging_specification;
    }
    
    public String getDeviceCategory() {
        return this.device_category;
    }
    
    public boolean getDisableCastFaststart() {
        return this.disableCastFaststart;
    }
    
    public boolean getDisableDataSaver() {
        return this.disableDataSaver;
    }
    
    public String getEnableLocalPlayback() {
        return this.enableLocalPlayback;
    }
    
    public String getEnableMdxRemoteControlLockScreen() {
        return this.enableMdxRemoteControlLockScreen;
    }
    
    public String getEnableMdxRemoteControlNotification() {
        return this.enableMdxRemoteControlNotification;
    }
    
    public ErrorLoggingSpecification getErrorLoggingSpecification() {
        return this.error_logging_specification;
    }
    
    public String getImagePref() {
        return this.image_pref;
    }
    
    public int getIpConnectivityPolicy() {
        return this.ip_connectivity_policy;
    }
    
    public int getJPlayerStreamErrorRestartCount() {
        return this.jPlayerRestartOnStreamErrors;
    }
    
    public String getMdxDisabled() {
        return this.disable_mdx;
    }
    
    public String getPTAggregationSize() {
        return this.pt_aggregation_size;
    }
    
    public String getPlayerType() {
        return this.type;
    }
    
    public int getRateLimitForGcmBrowseEvents() {
        return this.gcmBrowseEventRateLimit;
    }
    
    public int getRateLimitForGcmNListChangeEvents() {
        return this.gcmNListChangeEventRateLimit;
    }
    
    public String getSignUpEnabled() {
        return this.signup_enabled;
    }
    
    public String getSignUpTimeout() {
        return this.signup_timeout;
    }
    
    public String getSubtitleConfiguration() {
        return this.subtitle_configuration;
    }
    
    public SubtitleDownloadRetryPolicy getSubtitleDownloadRetryPolicy() {
        return this.subtitleDownloadRetryPolicy;
    }
    
    public int getUserSessionTimeoutDuration() {
        return this.user_session_timeout_duration;
    }
    
    public int getVideoResolutionOverride() {
        return this.videoResolutionOverride;
    }
    
    public VoipConfiguration getVoipConfiguration() {
        return this.voipConfig;
    }
    
    public int getVoipConfirmationDialogAllocationPercentage() {
        return this.voipConfirmationDialogAllocationPercentage;
    }
    
    public String getWebsocketDisabled() {
        return this.disable_websocket;
    }
    
    public boolean isDynecomSignInEnabled() {
        return this.enableDynecomSignIn;
    }
    
    public boolean isPlayBillingDisabled() {
        return this.disablePlayBilling;
    }
    
    public boolean isVoipEnabledOnDevice() {
        return this.voipEnabledOnDevice;
    }
    
    public boolean isWidevineL1Enabled() {
        return this.enableWidevineL1;
    }
    
    public boolean shouldAlertForMissingLocale() {
        return this.shouldAlertForLocaleSupport;
    }
    
    public boolean shouldForceLegacyCrypto() {
        return this.forceLegacyCrypto;
    }
    
    public boolean toIgnorePrelaodForPlayBilling() {
        return this.ignorePreloadForPlayBilling;
    }
}
