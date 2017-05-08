// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;

public class SubtitlePreference
{
    private static final String FIELD_BACK_COLOR = "backgroundColor";
    private static final String FIELD_BACK_OPACITY = "backgroundOpacity";
    private static final String FIELD_CHAR_COLOR = "charColor";
    private static final String FIELD_CHAR_OPACITY = "charOpacity";
    private static final String FIELD_CHAR_SIZE = "charSize";
    private static final String FIELD_CHAR_STLE = "charStyle";
    private static final String FIELD_EDGE_ATTRS = "charEdgeAttrs";
    private static final String FIELD_EDGE_COLOR = "charEdgeColor";
    public static final String FIELD_SUBTITLE_DEFAULT = "subtitleDefault";
    public static final String FIELD_SUBTITLE_OVERRIDE = "subtitleOverride";
    private static final String FIELD_WIN_COLOR = "windowColor";
    private static final String FIELD_WIN_OPACITY = "windowOpacity";
    private static final String TAG = "nf_subtitlePreference";
    private String backgroundColor;
    private String backgroundOpacity;
    private String charColor;
    private String charEdgeAttrs;
    private String charEdgeColor;
    private String charOpacity;
    private String charSize;
    private String charStyle;
    private String windowColor;
    private String windowOpacity;
    
    public SubtitlePreference(final String s) {
        try {
            JSONObject jsonObject;
            if (StringUtils.isEmpty(s)) {
                jsonObject = new JSONObject();
            }
            else {
                jsonObject = new JSONObject(s);
            }
            this.setCharOpacity(JsonUtils.getString(jsonObject, "charOpacity", (String)null));
            this.setBackgroundOpacity(JsonUtils.getString(jsonObject, "backgroundOpacity", (String)null));
            this.setWindowOpacity(JsonUtils.getString(jsonObject, "windowOpacity", (String)null));
            this.setCharColor(JsonUtils.getString(jsonObject, "charColor", (String)null));
            this.setBackgroundColor(JsonUtils.getString(jsonObject, "backgroundColor", (String)null));
            this.setWindowColor(JsonUtils.getString(jsonObject, "windowColor", (String)null));
            this.setCharEdgeColor(JsonUtils.getString(jsonObject, "charEdgeColor", (String)null));
            this.setCharEdgeAttrs(JsonUtils.getString(jsonObject, "charEdgeAttrs", (String)null));
            this.setCharSize(JsonUtils.getString(jsonObject, "charSize", (String)null));
            this.setCharStyle(JsonUtils.getString(jsonObject, "charStyle", (String)null));
        }
        catch (JSONException ex) {
            Log.d("nf_subtitlePreference", "failed to create json string=" + s + " exception =" + ex);
        }
    }
    
    public String getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public String getBackgroundOpacity() {
        return this.backgroundOpacity;
    }
    
    public String getCharColor() {
        return this.charColor;
    }
    
    public String getCharEdgeAttrs() {
        return this.charEdgeAttrs;
    }
    
    public String getCharEdgeColor() {
        return this.charEdgeColor;
    }
    
    public String getCharOpacity() {
        return this.charOpacity;
    }
    
    public String getCharSize() {
        return this.charSize;
    }
    
    public String getCharStyle() {
        return this.charStyle;
    }
    
    public String getWindowColor() {
        return this.windowColor;
    }
    
    public String getWindowOpacity() {
        return this.windowOpacity;
    }
    
    public void setBackgroundColor(final String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setBackgroundOpacity(final String backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
    }
    
    public void setCharColor(final String charColor) {
        this.charColor = charColor;
    }
    
    public void setCharEdgeAttrs(final String charEdgeAttrs) {
        this.charEdgeAttrs = charEdgeAttrs;
    }
    
    public void setCharEdgeColor(final String charEdgeColor) {
        this.charEdgeColor = charEdgeColor;
    }
    
    public void setCharOpacity(final String charOpacity) {
        this.charOpacity = charOpacity;
    }
    
    public void setCharSize(final String charSize) {
        this.charSize = charSize;
    }
    
    public void setCharStyle(final String charStyle) {
        this.charStyle = charStyle;
    }
    
    public void setWindowColor(final String windowColor) {
        this.windowColor = windowColor;
    }
    
    public void setWindowOpacity(final String windowOpacity) {
        this.windowOpacity = windowOpacity;
    }
    
    @Override
    public String toString() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("charOpacity", (Object)this.getCharOpacity());
                jsonObject.put("backgroundOpacity", (Object)this.getBackgroundOpacity());
                jsonObject.put("windowOpacity", (Object)this.getWindowOpacity());
                jsonObject.put("charColor", (Object)this.getCharColor());
                jsonObject.put("backgroundColor", (Object)this.getBackgroundColor());
                jsonObject.put("windowColor", (Object)this.getWindowColor());
                jsonObject.put("charEdgeColor", (Object)this.getCharEdgeColor());
                jsonObject.put("charEdgeAttrs", (Object)this.getCharEdgeAttrs());
                jsonObject.put("charSize", (Object)this.getCharSize());
                jsonObject.put("charStyle", (Object)this.getCharStyle());
                Log.d("nf_subtitlePreference", "user string=" + jsonObject.toString());
                return jsonObject.toString();
            }
            catch (JSONException ex) {
                Log.d("nf_subtitlePreference", "failed in json string " + ex);
                continue;
            }
            break;
        }
    }
}
