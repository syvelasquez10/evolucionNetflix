// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public class VoipConfiguration
{
    public static final int DEFAULT_SAMPLERATE = 8000;
    public static final int MAX_SAMPLERATE_48K = 48000;
    public static final int MIN_SAMPLERATE_8K = 8000;
    private static String TAG;
    @SerializedName("enableVoip")
    private boolean enableVoip;
    @SerializedName("enableVoipOverData")
    private boolean enableVoipOverData;
    @SerializedName("enableVoipOverWiFi")
    private boolean enableVoipOverWiFi;
    @SerializedName("jitterThresholdInMs")
    private Threshold jitterThresholdInMs;
    @SerializedName("packetLosThresholdInPercent")
    private Threshold packetLosThresholdInPercent;
    @SerializedName("rttThresholdInMs")
    private Threshold rttThresholdInMs;
    @SerializedName("sampleRateInHz")
    private int sampleRateInHz;
    @SerializedName("sipThresholdInMs")
    private Threshold sipThresholdInMs;
    
    static {
        VoipConfiguration.TAG = "nf_log";
    }
    
    public VoipConfiguration() {
        this.enableVoip = true;
        this.enableVoipOverData = true;
        this.enableVoipOverWiFi = true;
        this.sampleRateInHz = 8000;
    }
    
    public static VoipConfiguration loadFromPreferences(Context context) {
        final Context context2 = null;
        final String stringPref = PreferenceUtils.getStringPref(context, "voip_configuration", null);
        Label_0028: {
            if (StringUtils.isEmpty(stringPref)) {
                Log.d(VoipConfiguration.TAG, "VOIP config not found in file system");
                context = null;
            }
            else {
                while (true) {
                    try {
                        context = (Context)FalkorParseUtils.getGson().fromJson(stringPref, VoipConfiguration.class);
                        try {
                            Log.d(VoipConfiguration.TAG, "VOIP config loaded from file system");
                            break Label_0028;
                        }
                        catch (Throwable t2) {}
                        final Throwable t;
                        Log.e(VoipConfiguration.TAG, "Failed to load VOIP config from file system", t);
                    }
                    catch (Throwable t) {
                        context = context2;
                        continue;
                    }
                    break;
                }
            }
        }
        Object o = context;
        if (context == null) {
            o = new VoipConfiguration();
        }
        return (VoipConfiguration)o;
    }
    
    public static VoipConfiguration saveToPreferences(final NetflixPreference netflixPreference, final VoipConfiguration voipConfiguration) {
        if (voipConfiguration == null) {
            netflixPreference.removePref("voip_configuration");
            Log.d(VoipConfiguration.TAG, "Subtitle retry policy not found, return default");
            return new VoipConfiguration();
        }
        netflixPreference.putStringPref("voip_configuration", FalkorParseUtils.getGson().toJson(voipConfiguration));
        return voipConfiguration;
    }
    
    public Threshold getJitterThresholdInMs() {
        return this.jitterThresholdInMs;
    }
    
    public Threshold getPacketLosThresholdInPercent() {
        return this.packetLosThresholdInPercent;
    }
    
    public Threshold getRttThresholdInMs() {
        return this.rttThresholdInMs;
    }
    
    public int getSampleRateInHz() {
        return this.sampleRateInHz;
    }
    
    public Threshold getSipThresholdInMs() {
        return this.sipThresholdInMs;
    }
    
    public boolean isEnableVoip() {
        return this.enableVoip;
    }
    
    public boolean isEnableVoipOverData() {
        return this.enableVoipOverData;
    }
    
    public boolean isEnableVoipOverWiFi() {
        return this.enableVoipOverWiFi;
    }
    
    @Override
    public String toString() {
        return "VoipConfiguration{enableVoip=" + this.enableVoip + ", enableVoipOverData=" + this.enableVoipOverData + ", enableVoipOverWiFi=" + this.enableVoipOverWiFi + ", rttThresholdInMs=" + this.rttThresholdInMs + ", jitterThresholdInMs=" + this.jitterThresholdInMs + ", sipThresholdInMs=" + this.sipThresholdInMs + ", packetLosThresholdInPercent=" + this.packetLosThresholdInPercent + ", sampleRateInHz=" + this.sampleRateInHz + '}';
    }
}
