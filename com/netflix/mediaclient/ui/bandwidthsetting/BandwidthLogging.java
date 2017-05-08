// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public final class BandwidthLogging
{
    public static final boolean PRINT_LOG_DATA = true;
    private static final String TAG = "nf_bw_Logging";
    @SerializedName("location")
    private String location;
    @SerializedName("newValue")
    private Boolean newValue;
    @SerializedName("oldValue")
    private Boolean oldValue;
    @SerializedName("settingName")
    private String type;
    
    public BandwidthLogging(final BandwidthLogging$SettingType bandwidthLogging$SettingType, final Boolean oldValue, final Boolean newValue, final BandwidthLogging$InvokeLocation bandwidthLogging$InvokeLocation) {
        this.type = bandwidthLogging$SettingType.getValue();
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.location = bandwidthLogging$InvokeLocation.getValue();
    }
    
    private static BandwidthLogging createInstance(final Context context, final BandwidthLogging$SettingType bandwidthLogging$SettingType, final boolean b, final BandwidthLogging$InvokeLocation bandwidthLogging$InvokeLocation) {
        return new BandwidthLogging(bandwidthLogging$SettingType, !b, b, bandwidthLogging$InvokeLocation);
    }
    
    public static void reportBandwidthSettingChange(final Context context, final BandwidthLogging$SettingType bandwidthLogging$SettingType, final boolean b) {
        ApmLogUtils.reportLocalSettingsChange(context, createInstance(context, bandwidthLogging$SettingType, b, null).toJsonString());
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_bw_Logging", "BandwidthLogging as json: " + json);
        }
        return json;
    }
}
