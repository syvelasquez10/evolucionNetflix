// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.net.URLEncoder;
import org.json.JSONObject;

public class SetConfigData extends BaseInvoke
{
    private static final String DATA = "data";
    private static final String METHOD = "setConfigData";
    private static final String NAME = "name";
    private static final String TAG = "SetConfigData";
    private static final String TARGET = "nrdp";
    
    public SetConfigData(final JSONObject jsonObject, final String s) {
        int n = 1;
        super("nrdp", "setConfigData");
        if (jsonObject == null || s == null) {
            return;
        }
        try {
            final StringBuilder sb = new StringBuilder();
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                if (n == 0) {
                    sb.append("&");
                }
                final String s2 = keys.next();
                sb.append(String.format("%s=%s", URLEncoder.encode(s2, "UTF-8"), URLEncoder.encode(jsonObject.getString(s2), "UTF-8")));
                n = 0;
            }
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("name", (Object)s);
            jsonObject2.put("data", (Object)sb.toString());
            this.arguments = jsonObject2.toString();
        }
        catch (Exception ex) {
            Log.e("SetConfigData", "Unable to setConfigData", ex);
        }
    }
}
