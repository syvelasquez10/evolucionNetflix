// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Swim extends BaseInvoke
{
    private static final String METHOD = "swim";
    private static final String PROPERTY_absolute = "absolute";
    private static final String PROPERTY_allowRebuffer = "allowRebuffer";
    private static final String PROPERTY_forceRebuffer = "forceRebuffer";
    private static final String PROPERTY_fuzz = "fuzz";
    private static final String PROPERTY_pts = "pts";
    private static final String TARGET = "media";
    
    public Swim(final int n) {
        super("media", "swim");
        this.setArguments(n, true, 0, true, false);
    }
    
    public Swim(final int n, final boolean b) {
        super("media", "swim");
        this.setArguments(n, true, 0, true, b);
    }
    
    public Swim(final int n, final boolean b, final int n2, final boolean b2) {
        super("media", "swim");
        this.setArguments(n, b, n2, b2, false);
    }
    
    public Swim(final int n, final boolean b, final int n2, final boolean b2, final boolean b3) {
        super("media", "swim");
        this.setArguments(n, b, n2, b2, b3);
    }
    
    private void setArguments(final int n, final boolean b, final int n2, final boolean b2, final boolean b3) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pts", n);
            jsonObject.put("absolute", b);
            jsonObject.put("fuzz", n2);
            jsonObject.put("allowRebuffer", b2);
            jsonObject.put("forceRebuffer", b3);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
