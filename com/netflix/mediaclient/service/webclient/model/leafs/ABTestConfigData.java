// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ABTestConfigData
{
    private static final String COPPOLA_1_AB_TEST_ID = "6729";
    private static final String MOTION_BB_AB_TEST_ID = "6930";
    private static final String PHONE_ORIENTATION_TEST_ID = "7129";
    private static final String PUSH_OPT_IN_TEST_ID = "7035";
    private static final String TAG = "nf_config";
    private static final String VOICE_SEARCH_AB_TEST_ID = "6786";
    private static List<String> testIds;
    @SerializedName("6729")
    private ABTestConfig coppola1Config;
    @SerializedName("6930")
    private ABTestConfig motionBBConfig;
    @SerializedName("7129")
    private ABTestConfig phoneOrientationConfig;
    @SerializedName("7035")
    private ABTestConfig pushOptInConfig;
    @SerializedName("6786")
    private ABTestConfig voiceSearchConfig;
    
    static {
        ABTestConfigData.testIds = new ArrayList<String>();
    }
    
    public static ABTestConfigData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_config", "Parsing abTestConfig from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, ABTestConfigData.class);
    }
    
    public static String getABTestIds(final Context context) {
        ABTestConfigData.testIds.clear();
        ABTestConfigData.testIds.add("6786");
        ABTestConfigData.testIds.add("6930");
        ABTestConfigData.testIds.add("7035");
        if (DeviceUtils.isNotTabletByContext(context)) {
            ABTestConfigData.testIds.add("6729");
            ABTestConfigData.testIds.add("7129");
        }
        return StringUtils.joinArray(ABTestConfigData.testIds.toArray(new String[ABTestConfigData.testIds.size()]));
    }
    
    public ABTestConfig getCoppola1ABTestConfig() {
        return this.coppola1Config;
    }
    
    public ABTestConfig getMotionBBTestConfig() {
        return this.motionBBConfig;
    }
    
    public ABTestConfig getPhoneOrientationConfig() {
        return this.phoneOrientationConfig;
    }
    
    public ABTestConfig getPushOptInConfig() {
        return this.pushOptInConfig;
    }
    
    public ABTestConfig getVoiceSearchABTestConfig() {
        return this.voiceSearchConfig;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_config", "ABTestConfigData as json: " + json);
        }
        return json;
    }
}
