// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleFailure;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetFailedSubtitleDownloadUrl extends BaseInvoke
{
    private static final String METHOD = "setFailedSubtitleDownloadUrl";
    private static final String PROPERTY_REASON = "reason";
    private static final String PROPERTY_URL = "url";
    private static final String TARGET = "android";
    
    public SetFailedSubtitleDownloadUrl(final String s, final IMedia$SubtitleFailure media$SubtitleFailure) {
        super("android", "setFailedSubtitleDownloadUrl");
        String s2 = s;
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_invoke", "Url can not be empty!");
            s2 = "";
        }
        if (media$SubtitleFailure == null) {
            throw new IllegalArgumentException("Reason can not be empty!");
        }
        this.setArguments(s2, media$SubtitleFailure);
    }
    
    private void setArguments(final String s, final IMedia$SubtitleFailure media$SubtitleFailure) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", (Object)s);
            jsonObject.put("reason", (Object)media$SubtitleFailure.toString());
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
        catch (Exception ex2) {
            Log.e("nf_invoke", "Unable to Log failed subtitle ", ex2);
        }
    }
}
