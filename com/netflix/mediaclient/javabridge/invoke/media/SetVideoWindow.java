// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.media.Display;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetVideoWindow extends BaseInvoke
{
    private static final String METHOD = "setVideoWindow";
    private static final String PROPERTY_HEIGHT = "height";
    private static final String PROPERTY_TRANSITION_DURATION = "transitionDuration";
    private static final String PROPERTY_WIDTH = "width";
    private static final String PROPERTY_X = "x";
    private static final String PROPERTY_Y = "y";
    private static final String TARGET = "media";
    private Display display;
    
    public SetVideoWindow(final Display display, final int n) {
        super("media", "setVideoWindow");
        this.setArguments(display, n);
        this.display = display;
    }
    
    private void setArguments(final Display ex, final int n) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "width";
            final JSONException ex2 = ex;
            final int n2 = ((Display)ex2).getWidth();
            jsonObject2.put(s, n2);
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = "height";
            final JSONException ex3 = ex;
            final int n3 = ((Display)ex3).getHeight();
            jsonObject3.put(s2, n3);
            final JSONObject jsonObject4 = jsonObject;
            final String s3 = "x";
            final JSONException ex4 = ex;
            final int n4 = ((Display)ex4).getX();
            jsonObject4.put(s3, n4);
            final JSONObject jsonObject5 = jsonObject;
            final String s4 = "y";
            final JSONException ex5 = ex;
            final int n5 = ((Display)ex5).getY();
            jsonObject5.put(s4, n5);
            final JSONObject jsonObject6 = jsonObject;
            final String s5 = "transitionDuration";
            final int n6 = n;
            jsonObject6.put(s5, n6);
            final SetVideoWindow setVideoWindow = this;
            final JSONObject jsonObject7 = jsonObject;
            final String s6 = jsonObject7.toString();
            setVideoWindow.arguments = s6;
            return;
        }
        catch (JSONException ex6) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "width";
                final JSONException ex2 = ex;
                final int n2 = ((Display)ex2).getWidth();
                jsonObject2.put(s, n2);
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = "height";
                final JSONException ex3 = ex;
                final int n3 = ((Display)ex3).getHeight();
                jsonObject3.put(s2, n3);
                final JSONObject jsonObject4 = jsonObject;
                final String s3 = "x";
                final JSONException ex4 = ex;
                final int n4 = ((Display)ex4).getX();
                jsonObject4.put(s3, n4);
                final JSONObject jsonObject5 = jsonObject;
                final String s4 = "y";
                final JSONException ex5 = ex;
                final int n5 = ((Display)ex5).getY();
                jsonObject5.put(s4, n5);
                final JSONObject jsonObject6 = jsonObject;
                final String s5 = "transitionDuration";
                final int n6 = n;
                jsonObject6.put(s5, n6);
                final SetVideoWindow setVideoWindow = this;
                final JSONObject jsonObject7 = jsonObject;
                final String s6 = jsonObject7.toString();
                setVideoWindow.arguments = s6;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public Display getDisplay() {
        return this.display;
    }
}
