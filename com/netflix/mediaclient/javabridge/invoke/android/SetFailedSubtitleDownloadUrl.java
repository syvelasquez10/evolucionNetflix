// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetFailedSubtitleDownloadUrl extends BaseInvoke
{
    private static final String METHOD = "setFailedSubtitleDownloadUrl";
    private static final String PROPERTY_URL = "url";
    private static final String TARGET = "android";
    
    public SetFailedSubtitleDownloadUrl(final String arguments) {
        super("android", "setFailedSubtitleDownloadUrl");
        if (StringUtils.isEmpty(arguments)) {
            throw new IllegalArgumentException("Url can not be empty!");
        }
        this.setArguments(arguments);
    }
    
    private void setArguments(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", (Object)s);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
        catch (Exception ex2) {
            Log.e("nf_invoke", "Unable to Log WifiLinkSpeed ", ex2);
        }
    }
}
