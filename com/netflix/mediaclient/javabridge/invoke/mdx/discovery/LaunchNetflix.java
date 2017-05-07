// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.discovery;

import java.util.Iterator;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.Map;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class LaunchNetflix extends BaseInvoke
{
    private static final String METHOD = "launchNetflix";
    private static final String PROPERTY_launchArgs = "launchArgs";
    private static final String PROPERTY_usn = "usn";
    private static final String TARGET = "mdx";
    
    public LaunchNetflix(final String s, final Map<String, String> map) {
        super("mdx", "launchNetflix");
        this.setArguments(s, map);
    }
    
    private void setArguments(final String s, final Map<String, String> map) {
        while (true) {
            while (true) {
                Label_0167: {
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        String s2 = new String();
                        final Iterator<String> iterator = map.keySet().iterator();
                        if (!iterator.hasNext()) {
                            jsonObject.put("usn", (Object)s);
                            jsonObject.put("launchArgs", (Object)s2);
                            this.arguments = jsonObject.toString();
                            return;
                        }
                        final String s3 = iterator.next();
                        final String s4 = s2 = s2 + s3 + "=" + map.remove(s3);
                        if (!map.isEmpty()) {
                            s2 = s4 + "&";
                            break Label_0167;
                        }
                        break Label_0167;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                        return;
                    }
                }
                continue;
            }
        }
    }
}
