// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
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
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Url can not be empty!");
        }
        if (media$SubtitleFailure == null) {
            throw new IllegalArgumentException("Reason can not be empty!");
        }
        this.setArguments(s, media$SubtitleFailure);
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
