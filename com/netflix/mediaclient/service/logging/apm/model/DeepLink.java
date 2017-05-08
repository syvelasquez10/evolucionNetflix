// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class DeepLink
{
    public static final String DEEPLINK_MSG_PARAMS = "deeplinkMsgParams";
    private static final String DEEP_LINK_PARAMS = "deeplinkParams";
    private static final String SOURCE = "source";
    private String mDeeplinkParams;
    private String mSource;
    
    public DeepLink(final String mSource, final String mDeeplinkParams) {
        this.mSource = mSource;
        this.mDeeplinkParams = mDeeplinkParams;
    }
    
    public static DeepLink createInstance(final String s, final String s2) {
        if (s == null && s2 == null) {
            return null;
        }
        return new DeepLink(s, s2);
    }
    
    public static DeepLink createInstance(final JSONObject jsonObject) {
        if (jsonObject != null) {
            final String string = JsonUtils.getString(jsonObject, "source", null);
            final String string2 = JsonUtils.getString(jsonObject, "deeplinkParams", null);
            if (string != null || string2 != null) {
                return new DeepLink(string, string2);
            }
        }
        return null;
    }
    
    public String getDeeplinkParams() {
        return this.mDeeplinkParams;
    }
    
    public String getSource() {
        return this.mSource;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.mSource == null) {
            jsonObject.put("source", (Object)this.mSource);
        }
        if (this.mDeeplinkParams == null) {
            jsonObject.put("deeplinkParams", (Object)this.mDeeplinkParams);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "DeepLink{source='" + this.mSource + '\'' + ", deeplinkParams='" + this.mDeeplinkParams + '\'' + '}';
    }
}
