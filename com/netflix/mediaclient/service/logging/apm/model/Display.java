// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class Display
{
    public static final String ASPECT_RATIO = "aspectRatio";
    public static final String CONNECTOR = "connector";
    public static final String HEIGHT = "height";
    public static final String HORIZONTAL_DPI = "horizontalDpi";
    public static final String REFRESH_RATE = "refreshRate";
    public static final String RESOLUTION = "resolution";
    public static final String SCAN_MODE = "scanMode";
    public static final String WIDTH = "width";
    private Display$AspectRatio aspectRatio;
    private Display$Connector connector;
    private Integer height;
    private int horizontalDpi;
    private Integer refreshRate;
    private Display$ScanMode scanMode;
    private Integer width;
    
    private Display() {
    }
    
    public Display(final Display$Connector connector, final Display$AspectRatio aspectRatio, final Integer width, final Integer height, final Integer refreshRate, final Display$ScanMode scanMode, final int horizontalDpi) {
        this.connector = connector;
        this.aspectRatio = aspectRatio;
        this.width = width;
        this.height = height;
        this.refreshRate = refreshRate;
        this.scanMode = scanMode;
        this.horizontalDpi = horizontalDpi;
    }
    
    public static Display createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final Display display = new Display();
        display.refreshRate = JsonUtils.getIntegerObject(jsonObject, "refreshRate", (Integer)null);
        display.aspectRatio = Display$AspectRatio.find(JsonUtils.getString(jsonObject, "aspectRatio", (String)null));
        final JSONObject jsonObject2 = JsonUtils.getJSONObject(jsonObject, "resolution", (JSONObject)null);
        if (jsonObject2 != null) {
            display.width = JsonUtils.getIntegerObject(jsonObject2, "width", (Integer)null);
            display.height = JsonUtils.getIntegerObject(jsonObject2, "height", (Integer)null);
        }
        final String string = JsonUtils.getString(jsonObject, "connector", (String)null);
        if (string != null) {
            display.connector = Display$Connector.valueOf(string);
        }
        return display;
    }
    
    public Display$Connector getConnector() {
        return this.connector;
    }
    
    public Integer getHeight() {
        return this.height;
    }
    
    public Integer getRefreshRate() {
        return this.refreshRate;
    }
    
    public Display$ScanMode getScanMode() {
        return this.scanMode;
    }
    
    public Integer getWidth() {
        return this.width;
    }
    
    public JSONObject toJSONObject() {
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
        jsonObject.put("horizontalDpi", this.horizontalDpi);
        return jsonObject;
    }
}
