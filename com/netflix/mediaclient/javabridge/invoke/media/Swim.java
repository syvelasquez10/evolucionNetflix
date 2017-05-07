// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "pts";
            final int n3 = n;
            jsonObject2.put(s, n3);
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = "absolute";
            final boolean b4 = b;
            jsonObject3.put(s2, b4);
            final JSONObject jsonObject4 = jsonObject;
            final String s3 = "fuzz";
            final int n4 = n2;
            jsonObject4.put(s3, n4);
            final JSONObject jsonObject5 = jsonObject;
            final String s4 = "allowRebuffer";
            final boolean b5 = b2;
            jsonObject5.put(s4, b5);
            final JSONObject jsonObject6 = jsonObject;
            final String s5 = "forceRebuffer";
            final boolean b6 = b3;
            jsonObject6.put(s5, b6);
            final Swim swim = this;
            final JSONObject jsonObject7 = jsonObject;
            final String s6 = jsonObject7.toString();
            swim.arguments = s6;
            return;
        }
        catch (JSONException ex) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "pts";
                final int n3 = n;
                jsonObject2.put(s, n3);
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = "absolute";
                final boolean b4 = b;
                jsonObject3.put(s2, b4);
                final JSONObject jsonObject4 = jsonObject;
                final String s3 = "fuzz";
                final int n4 = n2;
                jsonObject4.put(s3, n4);
                final JSONObject jsonObject5 = jsonObject;
                final String s4 = "allowRebuffer";
                final boolean b5 = b2;
                jsonObject5.put(s4, b5);
                final JSONObject jsonObject6 = jsonObject;
                final String s5 = "forceRebuffer";
                final boolean b6 = b3;
                jsonObject6.put(s5, b6);
                final Swim swim = this;
                final JSONObject jsonObject7 = jsonObject;
                final String s6 = jsonObject7.toString();
                swim.arguments = s6;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)jsonObject);
            }
            catch (JSONException jsonObject) {
                continue;
            }
            break;
        }
    }
}
