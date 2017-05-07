// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx;

import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.Map;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Configure extends BaseInvoke
{
    private static final String METHOD = "configure";
    private static final String PROPERTY_config = "config";
    private static final String TAG = "nf_invoke";
    private static final String TARGET = "mdx";
    
    public Configure(final Map<String, String> arguments) {
        super("mdx", "configure");
        this.setArguments(arguments);
    }
    
    private void setArguments(final Map<String, String> map) {
        JSONObject jsonObject;
        JSONObject jsonObject2;
        try {
            jsonObject = new JSONObject();
            jsonObject2 = new JSONObject();
            for (final String s : map.keySet()) {
                jsonObject2.put(s, (Object)map.get(s));
            }
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            return;
        }
        jsonObject.put("config", (Object)jsonObject2);
        this.arguments = jsonObject.toString();
    }
}
