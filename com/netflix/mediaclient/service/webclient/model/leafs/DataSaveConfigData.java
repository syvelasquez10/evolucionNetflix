// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class DataSaveConfigData
{
    @SerializedName("limitCellularBW")
    public ABTestConfigData abTestConfig_6733;
    @SerializedName("bitrateCapHdOn")
    public int bitrateCapHdOn;
    @SerializedName("bitrateCapSaveOff")
    public int bitrateCapSaveOff;
    @SerializedName("bitrateCapSaveOn")
    public int bitrateCapSaveOn;
    @SerializedName("dataSaveOn")
    public boolean dataSaveOn;
}
