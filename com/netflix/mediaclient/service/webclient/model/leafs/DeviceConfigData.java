// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeviceConfigData
{
    @SerializedName("breadcrumb_logging_specification")
    private BreadcrumbLoggingSpecification breadcrumb_logging_specification;
    @SerializedName("consolidated_logging_specification")
    private List<ConsolidatedLoggingSessionSpecification> consolidated_logging_specification;
    private String current_version;
    private String device_category;
    private String disable_mdx;
    private String disable_websocket;
    private String disable_widevine;
    @SerializedName("enableLocalPlayback")
    private String enableLocalPlayback;
    @SerializedName("enableMdxRemoteControlLockScreen")
    private String enableMdxRemoteControlLockScreen;
    @SerializedName("enableMdxRemoteControlNotification")
    private String enableMdxRemoteControlNotification;
    @SerializedName("error_logging_specification")
    private ErrorLoggingSpecification error_logging_specification;
    @SerializedName("gcmBrowseEventRateLimitInSecs")
    private int gcmBrowseEventRateLimit;
    @SerializedName("gcmNListChangeEventRateLimitInSecs")
    private int gcmNListChangeEventRateLimit;
    private String image_pref;
    @SerializedName("ip_connectivity_policy")
    private int ip_connectivity_policy;
    private String min_version;
    private String pt_aggregation_size;
    private String signup_enabled;
    private String signup_timeout;
    private String subtitle_configuration;
    private String type;
    @SerializedName("user_session_timeout_duration")
    private int user_session_timeout_duration;
    @SerializedName("videoResolutionOverride")
    private int videoResolutionOverride;
    
    public DeviceConfigData() {
        this.consolidated_logging_specification = new ArrayList<ConsolidatedLoggingSessionSpecification>();
        this.ip_connectivity_policy = -1;
        this.user_session_timeout_duration = -1;
    }
    
    public String getAppMinVresion() {
        return this.min_version;
    }
    
    public String getAppRecommendedVresion() {
        return this.current_version;
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
    
    public int getUserSessionTimeoutDuration() {
        return this.user_session_timeout_duration;
    }
    
    public int getVideoResolutionOverride() {
        return this.videoResolutionOverride;
    }
    
    public String getWebsocketDisabled() {
        return this.disable_websocket;
    }
    
    public String getWidevineDisabled() {
        return this.disable_widevine;
    }
}
