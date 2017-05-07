// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DeviceConfigData
{
    private String bitrate_cap;
    @SerializedName("consolidated_logging_specification")
    private List<ConsolidatedLoggingSessionSpecification> consolidated_logging_specification;
    private String current_version;
    private String device_category;
    private String disable_mdx;
    private String disable_websocket;
    private String disable_widevine;
    private String image_pref;
    @SerializedName("ip_connectivity_policy")
    private int ip_connectivity_policy;
    private String min_version;
    private String pt_aggregation_size;
    private String signup_enabled;
    private String signup_timeout;
    private String subtitle_configuration;
    private String type;
    
    public DeviceConfigData() {
        this.consolidated_logging_specification = new ArrayList<ConsolidatedLoggingSessionSpecification>();
        this.ip_connectivity_policy = -1;
    }
    
    public String getAppMinVresion() {
        return this.min_version;
    }
    
    public String getAppRecommendedVresion() {
        return this.current_version;
    }
    
    public String getBitrateCap() {
        return this.bitrate_cap;
    }
    
    public List<ConsolidatedLoggingSessionSpecification> getConsolidatedloggingSpecification() {
        return this.consolidated_logging_specification;
    }
    
    public String getDeviceCategory() {
        return this.device_category;
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
    
    public String getSignUpEnabled() {
        return this.signup_enabled;
    }
    
    public String getSignUpTimeout() {
        return this.signup_timeout;
    }
    
    public String getSubtitleConfiguration() {
        return this.subtitle_configuration;
    }
    
    public String getWebsocketDisabled() {
        return this.disable_websocket;
    }
    
    public String getWidevineDisabled() {
        return this.disable_widevine;
    }
}
