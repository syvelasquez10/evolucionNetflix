// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class ConfigData
{
    public ABTestConfigData abTestConfigData;
    public AccountConfigData accountConfig;
    @SerializedName("castKey")
    public CastKeyData castKeyData;
    @SerializedName("customerSupportVoipAuthorizations")
    public VoipAuthorizationData customerSupportVoipAuthorizations;
    public DeviceConfigData deviceConfig;
    @SerializedName("nrmInfo")
    public NrmConfigData nrmInfo;
    @SerializedName("nrmLanguages")
    public NrmLanguagesData nrmLang;
    @SerializedName("signInConfig")
    public SignInConfigData signInConfigData;
    public String streamingqoeJson;
    
    public ABTestConfigData getABTestConfigData() {
        return this.abTestConfigData;
    }
    
    public AccountConfigData getAccountConfig() {
        return this.accountConfig;
    }
    
    public CastKeyData getCastKeyData() {
        return this.castKeyData;
    }
    
    public VoipAuthorizationData getCustomerSupportVoipAuthorizations() {
        return this.customerSupportVoipAuthorizations;
    }
    
    public DeviceConfigData getDeviceConfig() {
        return this.deviceConfig;
    }
    
    public NrmConfigData getNrmConfigData() {
        return this.nrmInfo;
    }
    
    public NrmLanguagesData getNrmLanguagesData() {
        return this.nrmLang;
    }
    
    public SignInConfigData getSignInConfigData() {
        return this.signInConfigData;
    }
    
    public String getStreamingConfig() {
        return this.streamingqoeJson;
    }
    
    @Override
    public String toString() {
        return "ConfigData{deviceConfig=" + this.deviceConfig + ", accountConfig=" + this.accountConfig + ", abTestConfigData=" + this.abTestConfigData + ", streamingqoeJson='" + this.streamingqoeJson + '\'' + ", nrmInfo=" + this.nrmInfo + ", signInConfigData=" + this.signInConfigData + ", customerSupportVoipAuthorizations=" + this.customerSupportVoipAuthorizations + ", castKeyData=" + this.castKeyData + '}';
    }
}
