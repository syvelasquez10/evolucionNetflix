// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class Display
{
    public static final String ASPECT_RATIO = "aspectRatio";
    public static final String CONNECTOR = "connector";
    public static final String HEIGHT = "height";
    public static final String REFRESH_RATE = "refreshRate";
    public static final String RESOLUTION = "resolution";
    public static final String SCAN_MODE = "scanMode";
    public static final String WIDTH = "width";
    private AspectRatio aspectRatio;
    private Connector connector;
    private Integer height;
    private Integer refreshRate;
    private ScanMode scanMode;
    private Integer width;
    
    private Display() {
    }
    
    public Display(final Connector connector, final AspectRatio aspectRatio, final Integer width, final Integer height, final Integer refreshRate, final ScanMode scanMode) {
        this.connector = connector;
        this.aspectRatio = aspectRatio;
        this.width = width;
        this.height = height;
        this.refreshRate = refreshRate;
        this.scanMode = scanMode;
    }
    
    public static Display createInstance(final JSONObject jsonObject) throws JSONException {
        Display display;
        if (jsonObject == null) {
            display = null;
        }
        else {
            final Display display2 = new Display();
            display2.refreshRate = JsonUtils.getIntegerObject(jsonObject, "refreshRate", null);
            display2.aspectRatio = AspectRatio.find(JsonUtils.getString(jsonObject, "aspectRatio", null));
            final JSONObject jsonObject2 = JsonUtils.getJSONObject(jsonObject, "resolution", null);
            if (jsonObject2 != null) {
                display2.width = JsonUtils.getIntegerObject(jsonObject2, "width", null);
                display2.height = JsonUtils.getIntegerObject(jsonObject2, "height", null);
            }
            final String string = JsonUtils.getString(jsonObject, "connector", null);
            display = display2;
            if (string != null) {
                display2.connector = Connector.valueOf(string);
                return display2;
            }
        }
        return display;
    }
    
    public Connector getConnector() {
        return this.connector;
    }
    
    public Integer getHeight() {
        return this.height;
    }
    
    public Integer getRefreshRate() {
        return this.refreshRate;
    }
    
    public ScanMode getScanMode() {
        return this.scanMode;
    }
    
    public Integer getWidth() {
        return this.width;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (this.connector != null) {
            jsonObject.put("connector", (Object)this.connector.name());
        }
        if (this.aspectRatio != null) {
            jsonObject.put("aspectRatio", (Object)this.aspectRatio.getDesc());
        }
        if (this.scanMode != null) {
            jsonObject.put("scanMode", (Object)this.scanMode.name());
        }
        if (this.width != null && this.height != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject.put("resolution", (Object)jsonObject2);
            jsonObject2.put("height", (int)this.height);
            jsonObject2.put("width", (int)this.width);
        }
        if (this.refreshRate != null) {
            jsonObject.put("refreshRate", (int)this.refreshRate);
        }
        return jsonObject;
    }
    
    public enum AspectRatio
    {
        _16x9("16x9"), 
        _4x3("4x3");
        
        private String desc;
        
        private AspectRatio(final String desc) {
            this.desc = desc;
        }
        
        public static AspectRatio find(final String s) {
            if (AspectRatio._16x9.desc.equals(s)) {
                return AspectRatio._16x9;
            }
            if (AspectRatio._4x3.desc.equals(s)) {
                return AspectRatio._4x3;
            }
            return null;
        }
        
        public String getDesc() {
            return this.desc;
        }
    }
    
    public enum Connector
    {
        component, 
        composite, 
        hdmi, 
        internal;
    }
    
    public enum ScanMode
    {
        nterlaced, 
        progressive;
    }
}
