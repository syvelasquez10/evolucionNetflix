// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
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
    
    private void setArguments(final Display display, final int n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("width", display.getWidth());
            jsonObject.put("height", display.getHeight());
            jsonObject.put("x", display.getX());
            jsonObject.put("y", display.getY());
            jsonObject.put("transitionDuration", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
    
    public Display getDisplay() {
        return this.display;
    }
}
