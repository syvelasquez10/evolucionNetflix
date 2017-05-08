// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.suspend.model;

import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.apm.model.DeepLink;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.service.logging.client.model.SessionStartedEvent;

public class ForegroundSessionStartedEvent extends SessionStartedEvent
{
    private static final String APP_TRIGGER = "appTrigger";
    private static final String PAYLOAD = "sourceTypePayload";
    private static final String SESSION_NAME = "foreground";
    private static final String TAG = "nf_log";
    private static final String TRIGGER = "trigger";
    private ApplicationPerformanceMetricsLogging$UiStartupTrigger mAppTrigger;
    private DeepLink mDeepLink;
    
    public ForegroundSessionStartedEvent(final ApplicationPerformanceMetricsLogging$UiStartupTrigger mAppTrigger, final DeepLink mDeepLink) {
        super("foreground");
        this.mAppTrigger = mAppTrigger;
        this.mDeepLink = mDeepLink;
    }
    
    private static ApplicationPerformanceMetricsLogging$UiStartupTrigger toTrigger(final DeepLink deepLink) {
        if (Log.isLoggable()) {
            Log.d("nf_log", "toTrigger: " + deepLink);
        }
        ApplicationPerformanceMetricsLogging$UiStartupTrigger applicationPerformanceMetricsLogging$UiStartupTrigger;
        if (deepLink == null || StringUtils.isEmpty(deepLink.getSource())) {
            Log.d("nf_log", "Deeplink or source type empty, go for default.");
            applicationPerformanceMetricsLogging$UiStartupTrigger = ApplicationPerformanceMetricsLogging$UiStartupTrigger.dedicatedOnScreenIcon;
        }
        else {
            try {
                final int int1 = Integer.parseInt(deepLink.getSource());
                if ((applicationPerformanceMetricsLogging$UiStartupTrigger = ApplicationPerformanceMetricsLogging$UiStartupTrigger.lookup(int1)) == null) {
                    Log.w("nf_log", "Source type not found for " + int1 + ". Go for default.");
                    return ApplicationPerformanceMetricsLogging$UiStartupTrigger.dedicatedOnScreenIcon;
                }
            }
            catch (Throwable t) {
                Log.e("nf_log", "Failed to extract source type, go for default", t);
                return ApplicationPerformanceMetricsLogging$UiStartupTrigger.dedicatedOnScreenIcon;
            }
        }
        return applicationPerformanceMetricsLogging$UiStartupTrigger;
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mAppTrigger != null) {
            data.put("appTrigger", this.mAppTrigger.getSourceType());
        }
        else {
            data.put("appTrigger", ApplicationPerformanceMetricsLogging$UiStartupTrigger.dedicatedOnScreenIcon.getSourceType());
        }
        if (this.mDeepLink != null) {
            data.put("trigger", ApplicationPerformanceMetricsLogging$UiStartupTrigger.externalApp.getSourceType());
            if (StringUtils.isNotEmpty(this.mDeepLink.getDeeplinkParams())) {
                data.put("sourceTypePayload", (Object)this.mDeepLink.getDeeplinkParams());
            }
            return data;
        }
        data.put("trigger", ApplicationPerformanceMetricsLogging$UiStartupTrigger.dedicatedOnScreenIcon.getSourceType());
        return data;
    }
}
