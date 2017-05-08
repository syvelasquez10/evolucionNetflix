// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SendSubtitleQoe extends BaseInvoke
{
    private static final String METHOD = "sendSubtitleQoe";
    private static final String PROPERTY_DOWNLOADABLE_ID = "dlid";
    private static final String PROPERTY_EXPECTED = "expected";
    private static final String PROPERTY_MISSED = "missed";
    private static final String TARGET = "android";
    
    public SendSubtitleQoe(final String s, final int n, final int n2) {
        super("android", "sendSubtitleQoe");
        this.setArguments(s, n, n2);
    }
    
    private void setArguments(final String s, final int n, int n2) {
        if (!StringUtils.isEmpty(s)) {
            n2 = n - n2;
            if (Log.isLoggable()) {
                Log.d("nf_invoke", "Downloadable ID: " + s + ", expected: " + n + ", missed: " + n2);
            }
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("dlid", (Object)s);
                jsonObject.put("expected", n);
                jsonObject.put("missed", n2);
                this.arguments = jsonObject.toString();
                if (Log.isLoggable()) {
                    Log.d("nf_invoke", "Argument: " + this.arguments);
                }
            }
            catch (JSONException ex) {
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            }
            catch (Throwable t) {
                Log.e("nf_invoke", "Unable to log subtitle QOE", t);
            }
        }
    }
}
