// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.configuration.NflxSupportedLocales;
import com.google.gson.annotations.SerializedName;

public class ConfigData
{
    public AccountConfigData accountConfig;
    @SerializedName("customerSupportVoipAuthorizations")
    public VoipAuthorizationData customerSupportVoipAuthorizations;
    public DeviceConfigData deviceConfig;
    @SerializedName("supportedLanguages")
    public NflxSupportedLocales nflxSupportedLocales;
    public String streamingqoeJson;
    
    public AccountConfigData getAccountConfig() {
        return this.accountConfig;
    }
    
    public VoipAuthorizationData getCustomerSupportVoipAuthorizations() {
        return this.customerSupportVoipAuthorizations;
    }
    
    public DeviceConfigData getDeviceConfig() {
        return this.deviceConfig;
    }
    
    public NflxSupportedLocales getNflxSupportedLocales() {
        return this.nflxSupportedLocales;
    }
    
    public String getStreamingConfig() {
        return this.streamingqoeJson;
    }
    
    @Override
    public String toString() {
        return "ConfigData{deviceConfig=" + this.deviceConfig + ", accountConfig=" + this.accountConfig + ", streamingqoeJson='" + this.streamingqoeJson + '\'' + ", customerSupportVoipAuthorizations=" + this.customerSupportVoipAuthorizations + ", nflxSupportedLocales=" + this.nflxSupportedLocales + '}';
    }
}
