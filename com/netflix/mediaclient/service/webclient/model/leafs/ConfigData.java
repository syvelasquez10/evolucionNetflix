// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

public class ConfigData
{
    public AccountConfigData accountConfig;
    public DeviceConfigData deviceConfig;
    public String streamingqoeJson;
    
    public AccountConfigData getAccountConfig() {
        return this.accountConfig;
    }
    
    public DeviceConfigData getDeviceConfig() {
        return this.deviceConfig;
    }
    
    public String getStreamingConfig() {
        return this.streamingqoeJson;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("ConfigData [deviceConfig=");
        String string;
        if (this.deviceConfig != null) {
            string = this.deviceConfig.toString();
        }
        else {
            string = "null";
        }
        final StringBuilder append2 = append.append(string).append(", accountConfig=");
        String string2;
        if (this.accountConfig != null) {
            string2 = this.accountConfig.toString();
        }
        else {
            string2 = "null";
        }
        return append2.append(string2).append(", streamingqoeJson=").append(this.streamingqoeJson).append("]").toString();
    }
}
