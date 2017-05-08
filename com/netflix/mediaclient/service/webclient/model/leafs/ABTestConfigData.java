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
    public static final String AIM_LOW_PREFETCH_DP_TEST_ID = "7722";
    public static final String AIM_LOW_PREFETCH_LOLOMO_TEST_ID = "7882";
    public static final String AIM_LOW_TEXT_PLACEHOLDER = "7858";
    public static final String COPPOLA_1_AB_TEST_ID = "6729";
    public static final String COPPOLA_2_AB_TEST_ID = "6941";
    public static final String CW_PROGRESS_BAR_TEST_ID = "7151";
    public static final String DISPLAY_PAGE_REFRESH_TEST_ID = "7842";
    public static final String MEMENTO2_TEST_ID = "7827";
    public static final String MEMENTO_TEST_ID = "7131";
    public static final String MOTION_BB_AB_TEST_ID = "6930";
    public static final String OFFLINE_TUTORIAL_TEST_ID = "7756";
    public static final String ONRAMP_TEST_ID = "7714";
    public static final String PHONE_ORIENTATION_TEST_ID = "7129";
    public static final String SURVEY_TEST_ID = "7141";
    private static final String TAG = "nf_config";
    public static final String VOICE_SEARCH_AB_TEST_ID = "6786";
    private static ABTestConfigData mAbTestConfigData;
    private static List<String> testIds;
    @SerializedName("7722")
    private ABTestConfig aimLowPrefetchDPConfig;
    @SerializedName("7882")
    private ABTestConfig aimLowPrefetchLolomoConfig;
    @SerializedName("7858")
    private ABTestConfig aimLowTextPlaceholderConfig;
    @SerializedName("6729")
    private ABTestConfig coppola1Config;
    @SerializedName("6941")
    private ABTestConfig coppola2Config;
    @SerializedName("7151")
    private ABTestConfig cwProgressBarConfig;
    @SerializedName("7842")
    private ABTestConfig displayPageRefresh;
    @SerializedName("7827")
    private ABTestConfig memento2Config;
    @SerializedName("7131")
    private ABTestConfig mementoConfig;
    @SerializedName("6930")
    private ABTestConfig motionBBConfig;
    @SerializedName("7756")
    private ABTestConfig offlineTutorial;
    @SerializedName("7714")
    private ABTestConfig onrampConfig;
    @SerializedName("7129")
    private ABTestConfig phoneOrientationConfig;
    @SerializedName("7141")
    private ABTestConfig surveyConfig;
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
        ABTestConfigData.testIds.add("7842");
        ABTestConfigData.testIds.add("7151");
        ABTestConfigData.testIds.add("7141");
        ABTestConfigData.testIds.add("7131");
        ABTestConfigData.testIds.add("7827");
        ABTestConfigData.testIds.add("7714");
        ABTestConfigData.testIds.add("7756");
        ABTestConfigData.testIds.add("7882");
        ABTestConfigData.testIds.add("7722");
        ABTestConfigData.testIds.add("7858");
        if (DeviceUtils.isNotTabletByContext(context)) {
            ABTestConfigData.testIds.add("6729");
            ABTestConfigData.testIds.add("6941");
            ABTestConfigData.testIds.add("7129");
        }
        return StringUtils.joinArray(ABTestConfigData.testIds.toArray(new String[ABTestConfigData.testIds.size()]));
    }
    
    public static ABTestConfigData getRawABConfig() {
        return ABTestConfigData.mAbTestConfigData;
    }
    
    public ABTestConfig getAimLowPrefetchDPConfig() {
        return this.aimLowPrefetchDPConfig;
    }
    
    public ABTestConfig getAimLowPrefetchLolomoConfig() {
        return this.aimLowPrefetchLolomoConfig;
    }
    
    public ABTestConfig getAimLowTextPlaceholderConfig() {
        return this.aimLowTextPlaceholderConfig;
    }
    
    public ABTestConfig getBrandLoveSurveyConfig() {
        return this.surveyConfig;
    }
    
    public ABTestConfig getCWProgressBarConfig() {
        return this.cwProgressBarConfig;
    }
    
    public ABTestConfig getCoppola1ABTestConfig() {
        return this.coppola1Config;
    }
    
    public ABTestConfig getCoppola2ABTestConfig() {
        return this.coppola2Config;
    }
    
    public ABTestConfig getDisplayPageRefreshConfig() {
        return this.displayPageRefresh;
    }
    
    public ABTestConfig getMemento2Config() {
        return this.memento2Config;
    }
    
    public ABTestConfig getMementoConfig() {
        return this.mementoConfig;
    }
    
    public ABTestConfig getMotionBBTestConfig() {
        return this.motionBBConfig;
    }
    
    public ABTestConfig getOfflineTutorialConfig() {
        return this.offlineTutorial;
    }
    
    public ABTestConfig getOnRampConfig() {
        return this.onrampConfig;
    }
    
    public ABTestConfig getPhoneOrientationConfig() {
        return this.phoneOrientationConfig;
    }
    
    public ABTestConfig getVoiceSearchABTestConfig() {
        return this.voiceSearchConfig;
    }
    
    public void setRawABConfig(final ABTestConfigData mAbTestConfigData) {
        ABTestConfigData.mAbTestConfigData = mAbTestConfigData;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_config", "ABTestConfigData as json: " + json);
        }
        return json;
    }
}
